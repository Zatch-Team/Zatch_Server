package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.DefaultRes;
import com.zatch.zatchserver.ResponseMessage;
import com.zatch.zatchserver.StatusCode;
import com.zatch.zatchserver.domain.ChatRoom;
import com.zatch.zatchserver.dto.GetBlockResDto;
import com.zatch.zatchserver.dto.PostChatReqDto;
import com.zatch.zatchserver.service.ChatService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    @ApiOperation(value = "채팅방 생성", notes = "채팅방 생성 API")
    public ChatRoom createRoom(@RequestBody String name) {
        return chatService.createRoom(name);
    }

    // 현재 열려있는 채팅방
    @GetMapping
    @ApiOperation(value = "현재 열려있는 채팅방", notes = "현재 열려있는 채팅방 API")
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }

    // 모든 채팅방 리스트
    @GetMapping("/chat_list/{userId}")
    @ApiOperation(value = "모든 채팅방 리스트", notes = "모든 채팅방 리스트 API")
    public List<Map<String, Object>> getAllChatRoom(@PathVariable("userId") String userId) {
        return chatService.getAllChatRoom(userId);
    }

    // 채팅방 나가기
    @GetMapping("/chat_out/{userId}/{roomId}")
    @ApiOperation(value = "채팅방 나가기", notes = "채팅방 나가기 API")
    public String outChatRoom(@PathVariable("userId") String userId, @PathVariable("roomId") String roomId) {
        return chatService.outChatRoom(userId, roomId);
    }

    //(거래 후) 별점, 후기 등록하기

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PostChatReqDto.class,
                    examples = @Example(@ExampleProperty(value = "{'property1': 'value1', 'property2': 'value2'}", mediaType = MediaType.APPLICATION_JSON_VALUE)))
    })
    @PostMapping("/after_deal")
    @ApiOperation(value = "별점&후기 등록", notes = "별점&후기 등록 API")
    public ResponseEntity postStarReview(@RequestBody PostChatReqDto postChatReqDto) {
        try {
            Long send_user_id = postChatReqDto.getSend_user_id();
            Long receive_user_id = postChatReqDto.getReceive_user_id();
            String review_context = postChatReqDto.getReview_context();
            int star_rating = postChatReqDto.getStar_rating();
            System.out.println("send_user_id : " + send_user_id);
            chatService.after_deal(send_user_id, receive_user_id, review_context, star_rating);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.AFTER_CHAT_SUCCESS, new PostChatReqDto(send_user_id, receive_user_id, review_context, star_rating)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR, "Error After Chat(Review&Star)"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 채팅 참여자 목록 보기
    @GetMapping("/{userId}/{roomId}/profile")
    @ApiOperation(value = "채팅 참여자 목록 보기", notes = "채팅 참여자 목록 보기 API")
    public List<Map<String, Object>> profileChatRoom(@PathVariable("userId") String userId, @PathVariable("roomId") String roomId) {
        return chatService.profileChatRoom(userId, roomId);
    }

    // 차단 & 신고하기


}
