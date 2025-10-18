# Subject API Documentation

## Tổng quan
API này cung cấp các chức năng quản lý môn học (Subject) bao gồm:
- Lấy danh sách môn học
- Tạo môn học mới
- Cập nhật môn học
- Xóa môn học
- Tìm kiếm môn học

## Base URL
```
http://localhost:8080/api/v1/subjects
```

## Endpoints

### 1. Lấy tất cả môn học
**GET** `/api/v1/subjects`

**Response:**
```json
[
  {
    "id": "uuid",
    "subjectCode": "IT001",
    "subjectName": "Tên môn học",
    "studentsPerClass": 50,
    "numberOfClasses": 3,
    "credits": 3,
    "theoryHours": 30,
    "exerciseHours": 15,
    "projectHours": 0,
    "labHours": 0,
    "selfStudyHours": 60,
    "department": "Bộ môn",
    "examFormat": "Thi viết",
    "majorId": "major-uuid",
    "majorName": "Tên ngành"
  }
]
```

### 2. Lấy môn học theo ID
**GET** `/api/v1/subjects/{id}`

**Response:**
```json
{
  "id": "uuid",
  "subjectName": "Tên môn học",
  "studentsPerClass": 50,
  "numberOfClasses": 3,
  "credits": 3,
  "theoryHours": 30,
  "exerciseHours": 15,
  "projectHours": 0,
  "labHours": 0,
  "selfStudyHours": 60,
  "department": "Bộ môn",
  "examFormat": "Thi viết",
  "majorId": "major-uuid",
  "majorName": "Tên ngành"
}
```

### 3. Lấy môn học theo Major ID
**GET** `/api/v1/subjects/major/{majorId}`

### 4. Tìm kiếm môn học theo tên
**GET** `/api/v1/subjects/search?name={subjectName}`

### 5. Tìm kiếm môn học theo mã môn học
**GET** `/api/v1/subjects/search/code?code={subjectCode}`

### 6. Lấy môn học theo Department
**GET** `/api/v1/subjects/department/{department}`

### 7. Tạo môn học mới
**POST** `/api/v1/subjects`

**Request Body:**
```json
{
  "subjectCode": "IT001",
  "subjectName": "Lập trình Java",
  "studentsPerClass": 50,
  "numberOfClasses": 3,
  "credits": 3,
  "theoryHours": 30,
  "exerciseHours": 15,
  "projectHours": 0,
  "labHours": 0,
  "selfStudyHours": 60,
  "department": "Công nghệ thông tin",
  "examFormat": "Thi viết",
  "majorId": "major-uuid",
  "facultyId": "faculty-uuid"
}
```

**Logic xử lý:**
- Tìm major theo `majorId` VÀ `classYear` (khóa học)
- Nếu tìm thấy major với ID và khóa học tương ứng: Sử dụng major hiện có
- Nếu không tìm thấy: Tự động tạo major mới với thông tin:
  - `majorName`: Tên ngành (bắt buộc nếu tạo mới)
  - `numberOfStudents`: Sĩ số sinh viên (bắt buộc)
  - `classYear`: Khóa học (bắt buộc)
  - `facultyId`: ID khoa (bắt buộc)

**Response:** (201 Created)
```json
{
  "id": "generated-uuid",
  "subjectCode": "IT001",
  "subjectName": "Lập trình Java",
  "studentsPerClass": 50,
  "numberOfClasses": 3,
  "credits": 3,
  "theoryHours": 30,
  "exerciseHours": 15,
  "projectHours": 0,
  "labHours": 0,
  "selfStudyHours": 60,
  "department": "Công nghệ thông tin",
  "examFormat": "Thi viết",
  "majorId": "major-uuid",
  "majorName": "Tên ngành"
}
```

### 8. Cập nhật môn học
**PUT** `/api/v1/subjects/{id}`

**Request Body:** (Giống như tạo môn học)

### 9. Xóa môn học
**DELETE** `/api/v1/subjects/{id}`

**Response:** (204 No Content)

## Validation Rules

### SubjectRequest Validation:
- `subjectCode`: Bắt buộc, tối đa 50 ký tự, unique
- `subjectName`: Bắt buộc, tối đa 255 ký tự
- `studentsPerClass`: Bắt buộc, từ 1-200
- `numberOfClasses`: Bắt buộc, từ 1-50
- `credits`: Bắt buộc, từ 1-10
- `theoryHours`: Bắt buộc, >= 0
- `exerciseHours`: Bắt buộc, >= 0
- `projectHours`: Bắt buộc, >= 0
- `labHours`: Bắt buộc, >= 0
- `selfStudyHours`: Bắt buộc, >= 0
- `department`: Bắt buộc, tối đa 100 ký tự
- `examFormat`: Bắt buộc, tối đa 50 ký tự
- `majorId`: Bắt buộc

## Error Responses

### Validation Error (400 Bad Request)
```json
{
  "timestamp": "2024-01-01T10:00:00",
  "status": 400,
  "error": "Validation Failed",
  "message": "Invalid input data",
  "errors": {
    "subjectName": "Subject name is required",
    "credits": "Credits must be at least 1"
  }
}
```

### Not Found Error (404 Not Found)
```json
{
  "timestamp": "2024-01-01T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Subject not found with id: uuid"
}
```

## Cách sử dụng

1. **Khởi động ứng dụng:**
   ```bash
   mvn spring-boot:run
   ```

2. **Test API bằng curl:**
   ```bash
   # Lấy tất cả môn học
   curl -X GET http://localhost:8080/api/v1/subjects
   
   # Tạo môn học với major đã tồn tại
   curl -X POST http://localhost:8080/api/v1/subjects \
     -H "Content-Type: application/json" \
     -d '{
       "subjectCode": "IT001",
       "subjectName": "Lập trình Java",
       "studentsPerClass": 50,
       "numberOfClasses": 3,
       "credits": 3,
       "theoryHours": 30,
       "exerciseHours": 15,
       "projectHours": 0,
       "labHours": 0,
       "selfStudyHours": 60,
       "department": "Công nghệ thông tin",
       "examFormat": "Thi viết",
       "majorId": "existing-major-uuid",
       "facultyId": "faculty-uuid"
     }'
   
   # Tạo môn học với major mới (tự động tạo major)
   curl -X POST http://localhost:8080/api/v1/subjects \
     -H "Content-Type: application/json" \
     -d '{
       "subjectCode": "IT002",
       "subjectName": "Cơ sở dữ liệu",
       "studentsPerClass": 40,
       "numberOfClasses": 2,
       "credits": 3,
       "theoryHours": 30,
       "exerciseHours": 15,
       "projectHours": 0,
       "labHours": 0,
       "selfStudyHours": 60,
       "department": "Công nghệ thông tin",
       "examFormat": "Thi viết",
       "majorId": "CNTT",
       "facultyId": "faculty-uuid",
       "majorName": "Công nghệ thông tin",
       "numberOfStudents": 100,
       "classYear": "K67"
     }'
   ```

## Lưu ý
- Đảm bảo database MySQL đã được cấu hình và chạy
- Faculty phải tồn tại trước khi tạo Subject
- Nếu majorId không tồn tại, hệ thống sẽ tự động tạo major mới (cần cung cấp majorName và facultyId)
- Tất cả các trường bắt buộc phải được cung cấp khi tạo/cập nhật Subject
