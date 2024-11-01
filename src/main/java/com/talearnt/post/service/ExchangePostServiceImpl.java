package com.talearnt.post.service;

import com.talearnt.post.exchange.request.ExchangePostCreateReqDTO;
import com.talearnt.post.exchange.ExchangePostRepository;
import com.talearnt.post.exchange.entity.ExchangePost;
import com.talearnt.post.exchange.response.ExchangePostReadResDTO;
import com.talearnt.util.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class ExchangePostServiceImpl implements PostService {

    private final ExchangePostRepository exchangePostRepository;
    private final ModelMapper mapper;


    @Override
    public ResponseEntity<CommonResponse<String>> createPost(ExchangePostCreateReqDTO exchangePostReqDTO) {

        //DTO -> Server Data로 변환
        ExchangePost entity = mapper.map(exchangePostReqDTO,ExchangePost.class);
        log.info("DTO -> Entity로 변환된 값 : {}",entity);

        //User Entity의 userNo 값 설정
        //entity.getUser().setId(exchangePostReqDTO.getUserInfo().getUserId());

        //Data 저장
        exchangePostRepository.save(entity);


        return CommonResponse.success("재능 교환 게시글을 등록했습니다.");
    }
}
