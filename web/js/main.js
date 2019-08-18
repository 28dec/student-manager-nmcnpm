function show_all_students() {
	$("#tbl_students>tbody").empty();
	$.ajax({
		type: "POST",
		url: "Controller/Student",
		data: {
			"command": "GET_ALL_STUDENTS"
		},
		success: function (response) {
			console.log(response)
			response = JSON.parse(response)
			if(response['result'] === "OK"){
				var j = response['data']
				for (var i = 0; i < j.length; i++) {
					var student = j[i];
					console.log(student)
					var new_tr = "<tr><td>" + student['code'] + "</td><td>" + student['first_name'] + "</td><td>" + student['last_name'] + "</td><td>" + student['date_of_birth'] + "</td><td>" + student['place_of_birth'] + "</td><td>" + student['phonenumber'] + "</td><td>" + student['email'] + "</td></tr>"
					$("#tbl_students>tbody").append(new_tr);
				}
			} else {
				alert(data);
			}
		}
	})
}
$(document).ready(function(){
	$("#frm_student").submit(function (e) {
		e.preventDefault();
		var form = $(this);
		console.log($(this).attr("id") + " submitted")
		var url = form.attr("action");
		console.log(form.serialize());
		$.ajax({
			type: "POST",
			url: url,
			data: form.serialize(),
			success: function (data) {
				console.log(data);
				show_all_students();
			},
			error: function(data){
				alert(data);
				console.log(data);
			}
		});
	})
	show_all_students();
})

$(document).on("click", ".btn-secondary", function () {
	$(this).closest("form").find("input[name=command").val($(this).attr("name"))
})