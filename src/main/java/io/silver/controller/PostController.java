package io.silver.controller;

import io.silver.data.Post;
import io.silver.service.PostService;
import io.silver.sys.Request;
import java.util.Scanner;

public class PostController {

    private Scanner sc = new Scanner(System.in);
    private PostService postService = new PostService();

    public void requestHandle(Request request) {

        switch(request.getFunction()) {
            case "add" :

                try{
                    System.out.println("게시물을 생성합니다!");

                    System.out.print("저장할 게시판 번호 : ");
                    String boardIdStr = sc.nextLine().trim();
                    int boardId = Integer.parseInt(boardIdStr);

                    boolean validBoard = postService.isValidBoard(boardId);
                    if(!validBoard) {
                        System.out.println("해당 게시판은 존재하지 않습니다. 게시판을 다시 선택해주세요. :,(");
                        break;
                    }

                    System.out.print("게시물 이름 : ");
                    String title = sc.nextLine().trim();

                    System.out.print("게시물 내용 : ");
                    String body = sc.nextLine().trim();

                    int id = postService.addPost(title, body);
                    postService.addOnBoard(boardId, id);

                    System.out.println(id + "번째 게시물이 성공적으로 생성되었습니다 ! :)");
                } catch (NullPointerException e) {
                    System.out.println("해당 게시물은 존재하지 않습니다.");
                } catch ( IndexOutOfBoundsException e) {
                    System.out.println("게시물 번호를 확인해주세요!");
                } catch ( NumberFormatException e) {
                    System.out.println("게시물 번호는 양의 정수로 입력하여 주세요 !");
                } catch (Exception e) {
                    System.out.println("알 수 없는 오류가 발생했습니다 !");
                    e.printStackTrace();
                }

                break;
            case "remove" :

                try{
                    System.out.println("게시물을 삭제하겠습니다.");

                    System.out.println("게시물은 양수로 입력해주세요 ^.^");
                    System.out.print("삭제할 게시물 번호 : ");

                    String removeIdStr = sc.nextLine().trim();

                    int removeId = Integer.parseInt(removeIdStr);

                    postService.removePostById(removeId);
                    System.out.println("게시물이 성공적으로 삭제되었습니다!");

                } catch (NullPointerException e) {
                    System.out.println("해당 게시물은 존재하지 않습니다.");
                } catch ( IndexOutOfBoundsException e) {
                    System.out.println("게시물 번호를 확인해주세요!");
                } catch ( NumberFormatException e) {
                    System.out.println("게시물 번호는 양의 정수로 입력하여 주세요 !");
                } catch (Exception e) {
                    System.out.println("알 수 없는 오류가 발생했습니다 !");
                    e.printStackTrace();
                }
                break;

            case "edit" :

                try{
                    System.out.println("게시물을 수정하겠습니다 !");

                    System.out.println("게시물은 양수로 입력해주세요 ^.^");

                    System.out.print("수정할 게시물 번호 : ");
                    String updateIdStr = sc.nextLine().trim();
                    int updateId = Integer.parseInt(updateIdStr);

                    System.out.print("수정할 게시물 제목 : ");
                    String updateTitle = sc.nextLine().trim();
                    System.out.print("수정할 게시물 내용 : ");
                    String updateBody = sc.nextLine().trim();

                    postService.editPostById(updateId, updateTitle, updateBody);
                } catch (NullPointerException e) {
                    System.out.println("해당 게시물은 존재하지 않습니다.");
                } catch ( IndexOutOfBoundsException e) {
                    System.out.println("게시물 번호를 확인해주세요!");
                } catch ( NumberFormatException e) {
                    System.out.println("게시물 번호는 양의 정수로 입력하여 주세요 !");
                } catch (Exception e) {
                    System.out.println("알 수 없는 오류가 발생했습니다 !");
                    e.printStackTrace();
                }
                break;

            case "view" :
                try{
                    System.out.println("게시물을 조회하겠습니다!");
                    System.out.println("게시물은 양수로 입력해주세요 ^.^");
                    System.out.print("조회할 게시물 번호 : ");

                    String viewIdStr = sc.nextLine().trim();

                    int viewId = Integer.parseInt(viewIdStr);

                    Post findPost = postService.viewPostById(viewId);

                    System.out.println(findPost.getId()+ "번 게시글");
                    System.out.println("작성일 : " + findPost.getCreatedAt());
                    if(findPost.getUpdatedAt()!=null) {
                        System.out.println("수정일 : " + findPost.getUpdatedAt());
                    }
                    System.out.println("제목 : " + findPost.getTitle());
                    System.out.println("내용 : " + findPost.getBody());
                } catch (NullPointerException e) {
                    System.out.println("해당 게시물은 존재하지 않습니다.");
                } catch ( IndexOutOfBoundsException e) {
                    System.out.println("게시물 번호를 확인해주세요!");
                } catch ( NumberFormatException e) {
                    System.out.println("게시물 번호는 양의 정수로 입력하여 주세요 !");
                } catch (Exception e) {
                    System.out.println("알 수 없는 오류가 발생했습니다 !");
                    e.printStackTrace();
                }

                break;

            default :
                System.out.println("존재하지 않는 명령어입니다.");
        }



    }
}
