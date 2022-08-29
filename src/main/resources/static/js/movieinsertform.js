/**
 * movieinsertform.html (js)
 */

   $(function() {
        $("#inputDate").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat: "yy-mm-dd",
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
        });
    });

	// 해당 페이지 불러올 때 장르 종류 호출
	$().ready(function() {
		// 장르 호출 ajax
		$.ajax({
			url:"/movie/genreList",
			type:"GET",
			error: function(){
				alert("에러 발생!");
			},
			success: function(data){
				// JSON으로 날아온 값 변환
				let obj = JSON.parse(data);
				// 정상적으로 데이터 송수신이 완료되었다면
				if (obj["status"] == 200) {
					let genreList = obj["result"];
					// 결과값 log로 남기기
					console.log("status : " + obj["status"]
						+ ", msg : " + obj["msg"]
						+ ", result : " + genreList);
					// 장르 리스트를 출력
					for (var i = 0; i < genreList.length; i++){
						$("#searchGenre").append("<option value='"+genreList[i]["sGenreId"]+"'>"+genreList[i]["sGenreName"]+"</option>");
					}
				}
				// 정상적이지 않은 status값이 온 경우
				else {
					alert(obj["status"] + " : " + obj["msg"]);
					console.log("status : ", obj["status"]);
					console.log("msg : ", obj["msg"]);
					console.log("result : ",obj["result"]);
				}

			}
		}); // 장르 호출 ajax 종료*/

		// 영화 등록 버튼 클릭
		$("#insertBtn").click(function() {

			let title = $("#inputTitle").val();
			let story = $("#inputStory").val();
			let image = $("#inputImage")[0].files[0];
			let date = $("#inputDate").val();
			let director = $("#inputDirector").val();
			let genre = $("#searchGenre").val();
			let formData = new FormData();
			//이미지를 이동시키기 위한 폼데이터
			formData.append("sMovieTitle", title);
			formData.append("sMovieStory", story);
			formData.append("imageFile", image);
			formData.append("dtOpeningDate", date);
			formData.append("sMovieDirector", director);
			formData.append("sMovieGenreName", genre);
			
			$(".errMsg").css("display", "none");

			// 미입력 방지용
			if (title == "" || title == null) {
				$("#inputTitle").focus();
				$(".titleMsg").css("display", "inline");
				return;
			}
			if (director == "" || director == null) {
				$("#inputDirector").focus();
				$(".directorMsg").css("display", "inline");
				return;
			}
			if (story == "" || story == null) {
				$("#inputStory").focus();
				$(".storyMsg").css("display", "inline");
				return;
			}
			if (image == "" || image == null) {
				$("#inputImage").focus();
				$(".imageMsg").css("display", "inline");
				return;
			}
			if (date == "" || date == null) {
				$("#inputDate").focus();
				$(".dateMsg").css("display", "inline");
				return;
			}
			// 영화 등록 ajax
			$.ajax({
				url:"/movie/insert",
				type:"post",
	            data:formData,
	            contentType: false,
	            processData: false,
				error : function(data) {
		            alert("영화 등록 에러 발생");
		        },
		        success : function(data) {
					// JSON형식으로 수신한 데이터 처리
					let obj = JSON.parse(data);
					if (obj["status"] == 400) {
						alert(obj["msg"]);
					} else if (obj["status"] == 500) {
						alert(obj["msg"]);
						console.log("status : " + obj["status"] + ", Exception : " + obj["exception"]);
					} else if (obj["status"] == 200) {
						alert(obj["msg"]);
						location.href="/";
					} else {
						alert("알 수 없는 오류 발생");
					}
		        }
		    }); //영화 등록 ajax 종료
		}); // 영화 버튼 클릭 function 종료
	});
    