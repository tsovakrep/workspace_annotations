$(document).ready(nn);

function nn() {
	$('#bttHello').click(mm);
}

function mm() {
	var fullname = $('#fullname').val();
	$.ajax({
		type : 'POST',
		data : {
			fullname : fullname
		},
		url : 'AjaxController',
		success : function(result) {
			$('#result1').html(result);
		}
	});
}