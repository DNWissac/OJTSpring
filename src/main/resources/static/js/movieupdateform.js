/**
 * movieupdateform.html (js)
 */
    $( function() {
        $( "#inputDate" ).datepicker({
          changeYear: true,
          changeMonth: true,
          dateFormat: "yy-mm-dd",
          dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
          monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
        });
      });

	function insertCheck()
	{
		var title = $("#inputTitle").val();
		var story = $("#inputStory").val();
		var date = $("#inputDate").val();
		var director = $("#inputDirector").val();

		$(".errMsg").css("display", "none");
		
		if (title == "" || title == null)
		{
			$("#inputTitle").focus();
			$(".titleMsg").css("display", "inline");
			return false;
		}
		if (director == "" || director == null)
		{
			$("#inputDirector").focus();
			$(".directorMsg").css("display", "inline");
			return false;
		}
		if (story == "" || story == null)
		{
			$("#inputStory").focus();
			$(".storyMsg").css("display", "inline");
			return false;
		}
		if (image == "" || image == null)
		{
			$("#inputImage").focus();
			$(".imageMsg").css("display", "inline");
			return false;
		}
		if (date == "" || date == null)
		{
			$("#inputDate").focus();
			$(".dateMsg").css("display", "inline");
			return false;
		}
		return true;
	}

	$().ready(function()
	{
		let movieSeq = $("#movieSeq").val();

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
					console.log("status : ", obj["status"]);
					console.log("msg : ", obj["msg"]);
					console.log("result : ", genreList);
					// 장르 리스트를 출력
					for (var i = 0; i < genreList.length; i++){
						$("#searchGenre").append("<option value='"+genreList[i]["sGenreId"]+"'>"+genreList[i]["sGenreName"]+"</option>");
					}
				}
				// 정상적이지 않은 status값이 온 경우
				else {
					alert(obj["status"] + " : " + obj["msg"]);

					console.log("status : " + obj["status"]
						+ ", msg : " + obj["msg"]
						+ ", result : " + obj["result"]);
				}
			}
		});

		// 영화 수정 버튼 클릭
		$("#updateBtn").click(function(){

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
			formData.append("nMovieSeq", movieSeq);

			$(".errMsg").css("display", "none");

			if (title == "" || title == null)
			{
				$("#inputTitle").focus();
				$(".titleMsg").css("display", "inline");
				return;
			}
			if (director == "" || director == null)
			{
				$("#inputDirector").focus();
				$(".directorMsg").css("display", "inline");
				return;
			}
			if (story == "" || story == null)
			{
				$("#inputStory").focus();
				$(".storyMsg").css("display", "inline");
				return;
			}
			if (date == "" || date == null)
			{
				$("#inputDate").focus();
				$(".dateMsg").css("display", "inline");
				return;
			}

			// 영화 수정 ajax
			$.ajax({
				url:"/movie/update",
				type:"post",
				data:formData,
				contentType: false,
				processData: false,
				error : function(data){
					console.log(data);
		            alert("영화 수정 에러 발생");
		        },
		        success : function(data){
					// JSON형식으로 수신한 데이터 처리
					let obj = JSON.parse(data);

					if (obj["status"] == 400) {
						alert(obj["msg"]);
					} else if (obj["status"] == 500) {
						alert(obj["msg"]);
						console.log(obj);
					} else if (obj["status"] == 200) {
						alert(obj["msg"]);
						location.href="/";
					} else {
						alert("알 수 없는 오류 발생");
					}
				}
		    }); //영화 수정 ajax 종료
		}); // 영화 버튼 클릭 function 종료
	});

	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function (e) {
				$('#imageView').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
    