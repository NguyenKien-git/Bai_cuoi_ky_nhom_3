# Tài Liệu Kiến Trúc Hệ Thống — Dự Án Java OOP Cuối Kỳ

## Mục đích tài liệu

Tài liệu này mô tả kiến trúc tổng quát cho các dự án Java OOP cuối kỳ có yêu cầu xây dựng đầy đủ Frontend, Backend và Mock Database bằng JSON. Kiến trúc này áp dụng thống nhất cho mọi bài toán, bất kể domain cụ thể là gì.

---

## 1. Tổng Quan Kiến Trúc 3 Tầng

Hệ thống được tổ chức theo mô hình 3 tầng tách biệt rõ ràng:

```
┌─────────────────────────────┐
│         FRONTEND            │  Giao diện người dùng (reatjs/angular)
├─────────────────────────────┤
│         BACKEND             │  Logic nghiệp vụ Java OOP
├─────────────────────────────┤
│      MOCK DATABASE          │  Dữ liệu lưu trữ dạng file JSON
└─────────────────────────────┘
```

Frontend và Backend giao tiếp qua HTTP request/response. Backend đọc và ghi dữ liệu trực tiếp từ file JSON thay vì kết nối database thực.

---

## 2. Tầng Frontend

### 2.1 Công nghệ
Frontend được xây dựng bằng các framework như angular, reactjs,...

### 2.2 Trách nhiệm
- Hiển thị giao diện người dùng.
- Thu thập dữ liệu nhập từ người dùng qua form.
- Gửi HTTP request đến Backend (dùng call API).
- Nhận response từ Backend (dạng JSON) và cập nhật giao diện.
- Xử lý thông báo lỗi và thành công cho người dùng.

### 2.3 Cấu trúc thư mục Frontend
- tuỳ theo từng framework

### 2.4 Giao tiếp với Backend
Frontend gửi request dạng JSON đến các endpoint của Backend:
- **GET**: lấy danh sách hoặc thông tin chi tiết.
- **POST**: tạo mới một đối tượng.
- **PUT**: cập nhật thông tin.
- **DELETE**: xoá một đối tượng.

Ví dụ request từ Frontend:
```javascript
fetch('/api/resource', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ field1: value1, field2: value2 })
})
.then(response => response.json())
.then(data => { /* cập nhật giao diện */ });
```

---

## 3. Tầng Backend — Java OOP

### 3.1 Công nghệ
Backend được viết bằng Java Springboot

### 3.2 Tổ Chức Package

```
src/
├── main/
│   └── Main.java
├── model/
│   ├── (Các class mô tả đối tượng dữ liệu)
│   └── (Abstract class / Interface)
├── service/
│   └── (Các class xử lý logic nghiệp vụ)
├── repository/
│   └── (Các class đọc/ghi file JSON)
├── handler/
│   └── (Các class nhận HTTP request và trả response)
└── utils/
    └── (Các hàm tiện ích dùng chung)
```

### 3.3 Trách Nhiệm Từng Package

**Package `model`** — Tầng dữ liệu
- Chứa các class mô tả đối tượng trong bài toán.
- Áp dụng Encapsulation: thuộc tính `private`, có getter/setter.
- Áp dụng Inheritance: class con kế thừa class cha hoặc abstract class.
- Áp dụng Abstraction: abstract class hoặc interface định nghĩa hành vi chung.
- Không chứa logic nghiệp vụ, không đọc/ghi file.

**Package `service`** — Tầng nghiệp vụ
- Chứa logic xử lý nghiệp vụ của hệ thống.
- Gọi xuống `repository` để lấy hoặc lưu dữ liệu.
- Áp dụng Polymorphism: gọi phương thức qua kiểu tổng quát (abstract class hoặc interface).
- Ném exception khi vi phạm business rule.
- Không biết gì về HTTP request/response.

**Package `repository`** — Tầng lưu trữ
- Chứa code đọc và ghi file JSON.
- Dùng thư viện `Gson` hoặc tự parse JSON bằng String để đọc dữ liệu.
- Mỗi loại đối tượng có một repository riêng.
- Trả về đối tượng Java cho tầng `service`.

**Package `handler`** — Tầng tiếp nhận HTTP
- Mỗi handler xử lý một nhóm endpoint liên quan.
- Nhận HTTP request, gọi `service` xử lý, trả về HTTP response dạng JSON.
- Bắt exception từ `service` và chuyển thành HTTP error response phù hợp (400, 404, 500).
- Không chứa logic nghiệp vụ.

**Package `utils`** — Tiện ích
- Chứa các hàm dùng chung: sinh mã tự động, validate dữ liệu, đọc/ghi file, format JSON.
- Các method thường là `static`.

### 3.4 Luồng Xử Lý Một Request

```
HTTP Request
     │
     ▼
  Handler
  (parse request, gọi service)
     │
     ▼
  Service
  (kiểm tra business rule, xử lý logic)
     │
     ▼
  Repository
  (đọc/ghi file JSON)
     │
     ▼
  File JSON
  (mock database)
     │
     ▼ (dữ liệu trả về ngược lên)
  Handler
  (format response JSON)
     │
     ▼
HTTP Response
```

### 3.5 Áp Dụng OOP Trong Backend

**Encapsulation** được áp dụng ở tầng `model`: mọi thuộc tính đều `private`, truy cập qua getter/setter. Setter kiểm tra tính hợp lệ của dữ liệu trước khi gán.

**Abstraction** được áp dụng qua abstract class hoặc interface định nghĩa các hành vi bắt buộc mà các class con phải cài đặt. Ví dụ một interface cho phương thức thanh toán buộc mọi hình thức thanh toán phải cài đặt phương thức `thanhToan()`.

**Inheritance** được áp dụng khi các class trong `model` có chung thuộc tính và hành vi. Class con kế thừa class cha, dùng `super()` trong constructor để tái sử dụng code khởi tạo.

**Polymorphism** được áp dụng ở tầng `service`: biến kiểu abstract class hoặc interface được dùng để gọi phương thức mà không cần biết class con cụ thể là gì. Điều này giúp `service` dễ mở rộng khi thêm loại đối tượng mới.

### 3.6 Exception Handling

- Tầng `model`/`service` ném exception (`IllegalArgumentException`, custom exception) khi vi phạm business rule.
- Tầng `handler` bắt exception bằng `try-catch` và chuyển thành HTTP response với status code phù hợp và message rõ ràng.
- Tầng `repository` dùng `try-catch` để xử lý lỗi đọc/ghi file.

---

## 4. Tầng Mock Database — File JSON

### 4.1 Vai trò
Thay vì kết nối database thực (MySQL, PostgreSQL), hệ thống đọc và ghi dữ liệu trực tiếp từ các file `.json` nằm trong thư mục `data/`. Đây là cách mock database đơn giản phù hợp với mục tiêu học tập.

### 4.2 Cấu trúc thư mục
```
data/
├── entities_a.json
├── entities_b.json
└── entities_c.json
```

Mỗi file JSON lưu danh sách đối tượng của một loại. Ví dụ cấu trúc một file JSON:
```json
[
  {
    "id": "001",
    "field1": "value1",
    "field2": 100
  },
  {
    "id": "002",
    "field1": "value2",
    "field2": 200
  }
]
```

### 4.3 Các thao tác với file JSON
- **Đọc tất cả**: đọc toàn bộ file, parse thành danh sách đối tượng Java.
- **Tìm theo ID**: đọc toàn bộ, lọc theo điều kiện.
- **Thêm mới**: đọc toàn bộ, thêm phần tử mới, ghi lại toàn bộ file.
- **Cập nhật**: đọc toàn bộ, tìm phần tử cần sửa, thay thế, ghi lại.
- **Xoá**: đọc toàn bộ, loại bỏ phần tử, ghi lại.

### 4.4 Thư viện xử lý JSON
Dùng thư viện **Gson** (Google) để chuyển đổi giữa đối tượng Java và chuỗi JSON:
```java
Gson gson = new Gson();
// Đọc: String JSON -> Object Java
MyClass obj = gson.fromJson(jsonString, MyClass.class);
// Ghi: Object Java -> String JSON
String json = gson.toJson(obj);
```

---

## 5. Giao Tiếp Giữa Các Tầng

### 5.1 Frontend ↔ Backend
Giao tiếp qua HTTP. Backend expose các endpoint REST đơn giản. Frontend gọi endpoint và xử lý JSON response.

### 5.2 Backend Handler ↔ Service
Handler gọi phương thức của Service, truyền dữ liệu đã parse từ request. Service trả về đối tượng Java hoặc ném exception.

### 5.3 Service ↔ Repository
Service gọi Repository để lấy hoặc lưu dữ liệu. Repository trả về đối tượng Java hoặc danh sách đối tượng. Service không biết dữ liệu được lưu ở đâu (file JSON hay database thực).

### 5.4 Repository ↔ File JSON
Repository chịu trách nhiệm hoàn toàn về việc đọc/ghi file. Các tầng trên không biết dữ liệu được lưu dưới dạng gì.

---

## 6. Nguyên Tắc Thiết Kế Quan Trọng

**Separation of Concerns**: mỗi tầng chỉ làm đúng một việc. Handler không xử lý nghiệp vụ. Service không đọc file. Repository không kiểm tra business rule.

**Dependency Direction**: sự phụ thuộc chỉ đi một chiều từ trên xuống. Handler phụ thuộc Service. Service phụ thuộc Repository. Repository phụ thuộc file JSON. Không có chiều ngược lại.

**Single Responsibility**: mỗi class chỉ có một lý do để thay đổi. Nếu logic thanh toán thay đổi, chỉ sửa class liên quan đến thanh toán, không ảnh hưởng các class khác.

**Open/Closed thông qua Interface**: hệ thống dễ mở rộng thêm loại đối tượng mới hoặc hình thức xử lý mới bằng cách thêm class implement interface, không cần sửa code hiện có.

---

## 7. Câu Hỏi Kiểm Tra Hiểu Biết Về Kiến Trúc

Những câu hỏi sau giúp sinh viên tự kiểm tra xem mình đã thực sự hiểu kiến trúc hay chưa:

- Nếu muốn chuyển từ lưu file JSON sang lưu database MySQL, chỉ cần sửa tầng nào?
- Tại sao business rule không được đặt trong Handler?
- Interface giúp ích gì khi hệ thống cần thêm một loại xử lý mới?
- Tại sao Repository không nên chứa logic kiểm tra dữ liệu hợp lệ?
- Nếu Frontend gọi sai endpoint, lỗi nên được xử lý ở tầng nào?
- Tại sao mỗi loại đối tượng nên có một Repository riêng thay vì gộp chung?
- Polymorphism được thể hiện cụ thể ở tầng nào trong kiến trúc này?
