/**
 * signinform.htmlhtml(js)
 */

	$().ready(function()
	{
		// 로그인 버튼 클릭 시
		$("#signinBtn").click(function()
		{
			let userEmail = $("#inputEmail").val();
			let userPassword = $("#inputPassword").val();
			
			$.ajax({
				url:"/signin",
				type:"post",
				data:{sUserEmail:userEmail,
					  sUserPassword:userPassword},
				error : function() {
		            alert("로그인 에러");
		        },
		        success : function(data){
					if ($.trim(data)== "OK") {
						alert("로그인에 성공하셨습니다.");
						location.replace("index");
					}
					else
						alert(data);
		        }
			});

			
		});
		
	});