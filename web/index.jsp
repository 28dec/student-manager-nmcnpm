<%-- 
	Document   : index
	Created on : Aug 17, 2019, 2:05:41 PM
	Author     : longnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Quản lý sinh viên</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> 
		<script src="js/main.js" type="text/javascript"></script>
	</head>
	<body>
		<div id="control_panel" class="container">
			<h3>QUẢN LÝ SINH VIÊN</h3>
			<form id="frm_student" action="Controller/Student">
				<div class="form-group">
				<label for="">Command</label>
				<input type="hidden" value="" name="command" class="form-control" placeholder="Command">
				</div>
				<div class="form-inline">
				<label for="ib_student_code">Mã SV: </label>
				<input type="text" name="ib_student_code" id="ib_student_code" class="form-control" placeholder="Mã sinh viên">
				</div>
				<div class="form-inline">
				<label for="ib_student_first_name">Họ đệm</label>
				<input type="text" name="ib_student_first_name" id="ib_student_first_name" class="form-control" placeholder="Họ đệm">
				<label for="ib_student_last_name">Tên:</label>
				<input type="text" name="ib_student_last_name" id="ib_student_last_name" class="form-control" placeholder="Tên">
				</div>
				<div class="form-inline">
				<label for="ib_student_date_of_birth">Ngày sinh: </label>
				<input type="text" name="ib_student_date_of_birth" id="ib_student_date_of_birth" class="form-control" placeholder="Ngày sinh">
				</div>
				<div class="form-inline">
				<label for="ib_student_place_of_birth">Quê quán: </label>
				<input type="text" name="ib_student_place_of_birth" id="ib_student_place_of_birth" class="form-control" placeholder="Quê quán">
				</div>
				<div class="form-inline">
				<label for="ib_student_phonenumber">Số điện thoại</label>
				<input type="text" name="ib_student_phonenumber" id="ib_student_phonenumber" class="form-control" placeholder="Số điện thoại">
				</div>
				<div class="form-inline">
				<label for="ib_student_email">Email</label>
				<input type="text" name="ib_student_email" id="ib_student_email" class="form-control" placeholder="Email">
				</div>
				<input class="btn-secondary" type="submit" value="Lưu SV mới" id="btn_create_student" name="CREATE_STUDENT">
				<input class="btn-secondary" type="submit" value="Lưu sửa tt SV" id="btn_update_student" name="UPDATE_STUDENT">
				<input class="btn-secondary" type="submit" value="Xóa SV" id="btn_delete_student" name="DELETE_STUDENT">
			</form>
		</div>
		<div id="view_panel" class="container my-5 py-5">
			<h3>Danh sách sinh viên</h3>
			<table class="table" id="tbl_students">
				<thead>
					<tr>
						<th>Mã SV</th>
						<th>Họ đệm</th>
						<th>Tên</th>
						<th>Ngày sinh</th>
						<th>Quê quán</th>
						<th>Số điện thoại</th>
						<th>Email</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</body>
</html>
