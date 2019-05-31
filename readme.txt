Download IntelliJ JDEA bản pro sau đó import project này vào
Chạy file EduBookApp.sql ở phần mềm quản lý mysql để tạo database
Ở project vào resources->application.properties thay đổi 2 dòng spring.datasource.username và spring.datasource.password thành tài khoản và mật khẩu mysql của ông
Sau đó chạy thử project tại localhost:8080

Api cho tất cả mọi người
  - api GET:
  /api/book/{id}: trả về 3 đối tượng: 1 là thông tin chi tiết quyển sách, 2 là id, tên người dùng,3 là kiểm tra xem người dùng đó đã like quyển sách chưa
  /api/category: trả về thông tin các category bao gồm id, name, imageLink
  /api/category/{id}: trả về thông tin các quyển sách(id,title,imageLink) có trong category đó
  /api/search?s=abc: trả về thông tin các quyển sách mà tên chứa 'abc'
  /api/auth/logout: đăng xuất
  
  - api POST:
  /api/auth/login: request 1 file json có dạng { "usernameOrEmail": "admin, "password":"123456 } để đăng nhập sau khi đăng nhập sẽ gen ra 1 mã token có type là bearer
  /api/auth/register: request 1 file json có dạng {"username":....., "email":....., "password":....., "confirmPassword":....} để đăng kí tài khoản. Khi có bad request thì t trả về file json báo lỗi BAD_REQUEST rồi ông xử lí tiếp

Api cho những người có tài khoản
  -api GET:
  /api/user: trả về thông tin chi tiết của user đang đăng nhập
  /api/user/{id} trả về thông tin chi tiết của user theo id
  /api/user/book/like: trả về thông tin sách user đang đăng nhập thích
  /api/book/{id}/like: like một quyển sách
  
  -api POST:
  /api/book/{id}/comment: request 1 file json có dạng {"content":.....} để bình luận quyển sách 
  
  -api DELETE:
  /api/book/{id}/comment/{commentId}/delete: xoá comment theo id, chỉ user của bình luận đó hoặc admin mới có quyền xoá
  /api/book/{id}/like/delete: huỷ like

UPDATE
    -api GET:
    /api/user/comment: trả về tất cả các comment của user hiện tại
    /api/user/download: trả về thông tin những quyển sách đã lưu lại
    -api POST:
    /api/book/{id}/download: request 1 file json có dạng {"currentPage":(1 số)} để lưu lại trang sách
    /api/user/avatar/upload: request 1 multipart/form-data để lưu ảnh đại diện của user
    -api DELETE:
    /api/user/avatar/delete: xoá ảnh đại diện;
    default avatar nằm ở /resources/img/default-avatar.png(Khi tạo tài khoản xong sẽ set avatar là default avatar này)