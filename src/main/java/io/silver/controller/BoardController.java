package io.silver.controller;

import io.silver.data.Board;
import io.silver.data.Post;
import io.silver.service.BoardService;
import io.silver.sys.Request;
import java.util.List;
import java.util.Scanner;

public class BoardController {

    private Scanner sc = new Scanner(System.in);

    BoardService boardService = new BoardService();

    public void requestHandle(Request request) {


        switch(request.getFunction()) {

            case "add" :
                System.out.println("게시판을 생성하겠습니다! ");
                System.out.print("게시판 이름 : ");
                String boardName = sc.nextLine().trim();

                System.out.print("게시판 소개 : ");
                String boardDesc = sc.nextLine().trim();

                int id = boardService.addBoard(boardName, boardDesc);
                System.out.println(id + "번째 게시판이 생성되었습니다! :)");
                System.out.println("게시판의 적극적인 이용 부탁드립니다 ~ ");

                break;
            case "edit" :

                try{

                    System.out.println("게시판을 수정하겠습니다.");

                    System.out.println("번호는 양의 정수로 입력해주세요 ~ ^.^");
                    System.out.print("수정할 게시판의 번호 : ");
                    String editIdStr = sc.nextLine().trim();
                    int editId = Integer.parseInt(editIdStr);

                    System.out.print("수정할 게시판의 이름 : ");
                    String editName = sc.nextLine().trim();

                    System.out.print("수정할 게시판의 설명 : ");
                    String editDesc = sc.nextLine().trim();

                    boardService.editBoardById(editId, editName, editDesc);

                } catch (NullPointerException e) {
                    System.out.println("해당 게시판은 존재하지 않습니다.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("게시판의 번호를 다시 확인해주세요.");
                } catch ( NumberFormatException e){
                    System.out.println("게시판의 번호는 양의 정수로만 입력해주세요 ~");
                } catch (Exception e) {
                    System.out.println("알 수 없는 오류가 발생했습니다...");
                    e.printStackTrace();
                }
                break;
            case "remove" :

                try{
                    System.out.println("게시판을 삭제하겠습니다 !");
                    System.out.println("정말 삭제를 원하시는 건가요? (삭제를 원하실 경우 Y나 y를 입력해주세요 !");
                    System.out.println("그렇지 않을 경우, 삭제는 취소 됩니다.");
                    String opinion = sc.nextLine().trim();

                    if(opinion.equals("Y") || opinion.equals("y")) {
                        System.out.println("번호는 양의 정수로 입력해주세요 ~ ^.^");
                        System.out.print("삭제할 게시판 번호 : ");
                        String removeIdStr = sc.nextLine().trim();
                        int removeId = Integer.parseInt(removeIdStr);

                        boardService.removeBoardById(removeId);
                    } else {
                        System.out.println("게시판 삭제를 취소합니다 ! 게시판의 적극적인 이용 부탁드립니다 :)");
                    }
                } catch (NullPointerException e) {
                    System.out.println("이미 존재하지 않는 게시판입니다.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("게시판 번호를 다시 확인해주세요.");
                } catch (NumberFormatException e) {
                    System.out.println("게시판 번호는 양의 정수로만 입력해주세요.");
                } catch (Exception e) {
                    System.out.println("알 수 없는 오류가 발생했습니다.");
                    e.printStackTrace();
                }

                break;
            case "view" :

                try{

                    System.out.println("게시판을 조회하겠습니다.");

                    System.out.println("번호는 양의 정수로 입력해주세요 ~ ^.^");
                    System.out.print("조회할 게시판 번호 : ");
                    String viewIdStr = sc.nextLine().trim();
                    int viewId = Integer.parseInt(viewIdStr);

                    Board findBoard = boardService.viewBoardById(viewId);

                    System.out.println("다음은 " + findBoard.getBoardName() + "에 저장된 게시글 목록입니다.");
                    System.out.println("게시판 설명 : " + findBoard.getDescription() );

                    List<Post> postList = findBoard.getPostList();

                    System.out.println(" 글 번호 | 글 제목 | 글 내용 | 작성일 | 수정일 ");
                    for (Post post : postList) {
                        System.out.println(post.getId() + " | "
                                + post.getTitle() + " | "
                                + post.getBody() + " | "
                                + post.getCreatedAt() + " | "
                                + post.getUpdatedAt());
                    }
                } catch (NullPointerException e) {
                    System.out.println("해당 게시판은 존재하지 않습니다.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("게시판 번호를 다시 확인해주세요.");
                } catch (NumberFormatException e) {
                    System.out.println("게시판 번호는 양의 정수로만 입력해주세요.");
                } catch (Exception e) {
                    System.out.println("알 수 없는 오류가 발생했습니다.");
                    e.printStackTrace();
                }


                break;
        }
    }

}
