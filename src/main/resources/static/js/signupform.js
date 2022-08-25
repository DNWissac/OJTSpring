/**
 * signupform.htmlhtml(js)
 */

	$().ready(function() {
		// 프론트 유효성 검사
		$("#signupBtn").click(function() {
			$(".errMsg").css("display", "none");
			$(".pwdMsg").css("display", "none");
			$(".rePwdMsg").css("display", "none");

			let errCount = 0;
			let email = $("#sUserEmail").val();
			let password = $("#sUserPassword").val();
			let rePwd = $("#inputRePassword").val();
			let nickName = $("#sUserNickName").val();

			// 비밀번호 유효성검사용
			let num = password.search(/[0-9]/g);
			let eng = password.search(/[a-z]/ig);
			let spe = password.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

			// 이메일 유효성검사용
			let exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

			// 이메일이 입력되지 않은 경우
			if (email == "" || email == null){
				$("#sUserEmail").focus();
				$(".emailMsg").text("이메일을 입력해 주세요.");
				$(".emailMsg").css("display", "inline");
				errCount++;
				return;
			}

			// 이메일이 유효하지 않은 경우
			if(exptext.test(email)==false){
				$("#sUserEmail").focus();
				$(".emailMsg").text("이메일 형식이 올바르지 않습니다.");
				$(".emailMsg").css("display", "inline");
				errCount++;
				return;
			}

			// 이메일의 길이가 너무 긴 경우
			if(email.length > 100){
				$("#sUserEmail").focus();
				$(".emailMsg").text("이메일이 너무 깁니다.");
				$(".emailMsg").css("display", "inline");
				errCount++;
				return;
			}

			// 비밀번호가 입력되지 않은 경우
			if (password == "" || password == null){
				$("#sUserPassword").focus();
				$(".pwdMsg").text("비밀번호를 입력해 주세요.");
				$(".pwdMsg").css("display", "inline");
				errCount++;
				return;
			}
			
			// 비밀번호의 길이가 8~20 이내가 아닐 경우
			if (password.length < 8 || password.length > 20){
				$("#sUserPassword").focus();
				$(".pwdMsg").text("8자리~ 20자리 이내로 입력해주세요.");
				$(".pwdMsg").css("display", "inline");
				errCount++;
				return;
			}

			
			// 비밀번호에 공백이 있을 경우
			if (password.search(/\s/) != -1){
				$("#sUserPassword").focus();
				$(".pwdMsg").text("비밀번호는 공백 없이 입력해주세요.");
				$(".pwdMsg").css("display", "inline");
				errCount++;
				return;
			}
			
			
			// 비밀번호가 영문/숫자/특수문자가 혼합되어 있지 않은 경우
			if(num < 0 || eng < 0 || spe < 0){
				$("#sUserPassword").focus();
				$(".pwdMsg").text("영문, 숫자, 특수문자를 혼합하여 입력해주세요.");
				$(".pwdMsg").css("display", "inline");
				errCount++;
				return;
			}
			
			// 비밀번호가 일치하지 않는 경우
			if (rePwd != password){
				$("#inputRePassword").focus();
				$(".rePwdMsg").text("비밀번호가 일치하지 않습니다.");
				$(".rePwdMsg").css("display", "inline");
				errCount++;
				return;
			}

			// 닉네임이 입력되지 않은 경우
			if (nickName == "" || nickName == null){
				$("#sUserNickName").focus();
				$(".nickMsg").text("닉네임을 입력해 주세요.");
				$(".nickMsg").css("display", "inline");
				errCount++;
				return;
			}

			// 닉네임의 길이 제한
			if (nickName.length<2 || nickName.length>8){
				$("#sUserNickName").focus();
				$(".nickMsg").text("닉네임은 2~8자 사이로 입력해 주세요.");
				$(".nickMsg").css("display", "inline");
				errCount++;
				return;
			}
			
			// 닉네임에 빈칸이 포함된 경우
			if (nickName.search(/\s/) != -1) {
				$("#sUserNickName").focus();
				$(".nickMsg").text("닉네임은 공백 없이 입력해주세요.");
				$(".nickMsg").css("display", "inline");
				errCount++;
				return;
			}

			if (errCount == 0) {
				$(".signform").submit();
			}

		}); // #signupBtn click 함수 종료
	});