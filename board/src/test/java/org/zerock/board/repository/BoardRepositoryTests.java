package org.zerock.board.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;
    
    @Test
    @DisplayName("게시글테스트")
    public void insertBoard(){
        IntStream.rangeClosed(1,100).forEach(i ->{
            Member member = Member.builder().email("user" + i + "@aaa.com").build();

            Board board = Board.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);

        });
    }

    @Test
    @Transactional
    @DisplayName("read test")
    public void testRead(){
        Optional<Board> result = boardRepository.findById(100L);

        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    @DisplayName("테스트_작성자가져오기")
    public void testReadWithWriter(){
        Object result = boardRepository.getBoardWithWriter(100L);

        System.out.println(Arrays.toString((Object[]) result));
    }

    @Test
    @DisplayName("테스트_게시글,댓글가져오기")
    public void testGetBoardWithReply(){
        List<Object[]> result = boardRepository.getBoardWithReply(100L);

        for(Object[] arr : result){
            System.out.println(Arrays.toString(arr));
        }

    }


}
