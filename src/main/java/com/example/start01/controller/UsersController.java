package com.example.start01.controller;

import com.example.start01.dao.UsersDao;
import com.example.start01.dto.LoginRequest;
import com.example.start01.dto.NicknameCheckDto;
import com.example.start01.dto.StoreDto;
import com.example.start01.dto.UsersDto;
import com.example.start01.service.UsersService;
import com.example.start01.utils.JwtTokenProvider;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> UsersInsert(@RequestBody UsersDto usersDto){
        usersService.UsersInsert(usersDto);
        return ResponseEntity.ok("회원가입 성공!");
    }

    // 닉네임 중복 체크
    @GetMapping("/nicknameCheck")
    public ResponseEntity<NicknameCheckDto> NicknameCheck(@RequestParam String nickname) {
        boolean isDuplicate = usersService.NicknameSelect(nickname);
        return ResponseEntity.ok(new NicknameCheckDto(isDuplicate));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UsersDto user = usersService.login(email, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }

        // 토큰 생성
        String token = jwtTokenProvider.createToken(email);

        // 토큰 + 사용자 정보를 JSON 형태로 전달
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);
        return ResponseEntity.ok(response);
    }

    // 주소 업데이트
    @PutMapping("/addressUpdate")
    public void updateAddress(@RequestBody UsersDto usersDto) {
        usersService.updateAddress(usersDto);
        System.out.println("---업데이트된 usersDto---:" + usersDto);
    }

    // 주소 업데이트 + 상세주소
    @PostMapping("/addressUpdateDetail")
    public void updateAddressAndDetail(@RequestBody UsersDto usersDto) {
        usersDao.updateAddressAndDetail(usersDto);
    }

    // 주소 불러오기
    @GetMapping("/getUserAddress/{userId}")
    public UsersDto getUserAddress(@PathVariable Integer userId) {
        return usersService.getUserAddress(userId);
    }

    // 토큰으로 유저 정보 조회 (이메일 기반)
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            String email = jwtTokenProvider.getEmail(token);

            // 비밀번호 확인 없이 유저 정보만 조회
            UsersDto user = usersService.findByEmail(email);
            if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 없음");

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 유효하지 않음");
        }
    }

    // 다 불러오기
    @GetMapping("/selectUserinfo")
    public List<UsersDto> selectAll(){
        return usersService.selectAll();
    }

    // 비밀번호 업데이트
    @PostMapping("/updatePassword")
    public String updatePassword(@RequestBody UsersDto usersDto){
        boolean updated = usersService.updatePassword(usersDto);
        return updated ? "success" : "fail";
    }

    @PutMapping("/updateProfile")
    public void updateProfile(@RequestBody UsersDto usersDto) {
        usersService.updateProfile(usersDto);
    }

    // 프로필 이미지 업로드
    @PostMapping("/uploadProfileImage")
    public ResponseEntity<Map<String, Object>> uploadProfileImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Integer userId
    ) {
        try {
            // 1. 확장자 추출
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            String fileName = userId + "." + ext;

            // 2. 저장 경로 지정
            String uploadDir = "C:/upload/profileimg/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 3. 저장
            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath.toFile());

            // 4. DB 저장할 상대 경로
            String dbPath = "/image/profileimg/" + fileName;
            usersService.updateProfileUrl(userId, dbPath);
            System.out.println("Updated profileUrl in DB: " + dbPath);

            // 5. 클라이언트에 전달
            Map<String, Object> response = new HashMap<>();
            response.put("profileUrl", dbPath);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 비밀번호 재설정
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody UsersDto usersDto){
        boolean updated = usersService.resetPassword(usersDto);
        return updated ? "success" : "fail";
    }

    // 회원 탈퇴 (비활성화)
    @PostMapping("/delete")
    public ResponseEntity<String> deleteUserByPost(@RequestBody Map<String, Integer> body) {
        int id = body.get("id");
        boolean deleted = usersService.unactiveUsers(id);
        if (deleted) {
            return ResponseEntity.ok("회원탈퇴 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원탈퇴 실패");
        }
    }
    // 여러 userId로 사용자 정보 조회
    @PostMapping("/findUsersByIds")
    public ResponseEntity<List<UsersDto>> findUsersByIds(@RequestBody List<Integer> userIds) {
        List<UsersDto> users = usersService.findUsersByIds(userIds);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/rating")
    public ResponseEntity<String> rateUser(@RequestBody Map<String, Object> body) {
        try {
            Integer userId = (Integer) body.get("userId");
            Integer score = (Integer) body.get("score");

            if (userId == null || score == null || score < 1 || score > 5) {
                return ResponseEntity.badRequest().body("잘못된 요청 데이터입니다.");
            }

            // 기존 userRating 조회 후 계산 처리
            UsersDto user = usersService.findById(userId);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
            }


            double currentRating = user.getUserRating();
            double adjusted = score - 3; // -2 ~ +2
            double newRating = currentRating + adjusted;
            newRating = Math.max(0, Math.min(100, newRating));

            // 업데이트 호출
            usersService.rateUser(userId, newRating);

            return ResponseEntity.ok("별점이 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 에러가 발생했습니다.");
        }
    }

    // 총 사용자 수
    @GetMapping("/totalCount")
    public int TotalCount() {
        return usersDao.TotalCount();
    }

    @GetMapping("/unactiveCount")
    public int unactiveCount() {
        return usersDao.unactiveCount();
    }

    @GetMapping("/banCount")
    public int banCount() {
        return usersDao.banCount();
    }

    @GetMapping("/selectAllAdmin")
    public List<UsersDto> selectAllAdmin() {
        return usersDao.selectAllAdmin();
    }
    @GetMapping("/selectAllActive")
    public List<UsersDto> selectAllActive() {
        return usersDao.selectAllActive();
    }

    // 룸 / 챗 관련 유저 정보 업데이트
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UsersDto usersDto) {
        boolean updated = usersService.updateUser(usersDto);
        if (updated) {
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or no changes made");
        }
    }

    @GetMapping("/userBtnCountRole/{role}")
    public int userBtnCountRole(@PathVariable String role) {
        return usersDao.userBtnCountRole(role);
    }
    @GetMapping("/userBtnCount/{role}")
    public List<UsersDto> userBtnCount(@PathVariable String role) {
        return usersDao.userBtnCount(role);
    }

    @GetMapping("/unactiveBan/{status}")
    public List<UsersDto> unactiveBan(@PathVariable String status) {
        return usersDao.unactiveBan(status);
    }
    @GetMapping("/userBanSearch")
    public List<UsersDto> userBanSearch(@RequestParam String type, @RequestParam String keyword){
        Map<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("keyword", keyword);
        return usersDao.userBanSearch(param);
    }
    @GetMapping("/userUnactiveSearch")
    public List<UsersDto> userUnactiveSearch(@RequestParam String type, @RequestParam String keyword){
        Map<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("keyword", keyword);
        return usersDao.userUnactiveSearch(param);
    }
    @GetMapping("/userSearchActive")
    public List<UsersDto> userSearchActive(@RequestParam String type, @RequestParam String keyword){
        Map<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("keyword", keyword);
        return usersDao.userSearchActive(param);
    }

    @GetMapping("/userSearch")
    public List<UsersDto> userSearch(@RequestParam String type, @RequestParam String keyword){
        Map<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("keyword", keyword);
        return usersDao.userSearch(param);
    }
    @GetMapping("/userOwnerSearch")
    public List<UsersDto> userOwnerSearch(@RequestParam String type, @RequestParam String keyword){
        Map<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("keyword", keyword);
        return usersDao.userOwnerSearch(param);
    }
    @GetMapping("/userSearchAdmin")
    public List<UsersDto> userSearchAdmin(@RequestParam String type, @RequestParam String keyword){
        Map<String, String> param = new HashMap<>();
        param.put("type", type);
        param.put("keyword", keyword);
        return usersDao.userSearchAdmin(param);
    }


}
