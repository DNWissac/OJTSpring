/**
 * movieinsertform.html (js)
 */

   $(function(){
        $("#inputDate").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat: "yy-mm-dd",
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
        });
    });

	// 해당 페이지 불러올 때 장르 종류 호출
	/*$().ready(function()
	{
		// 장르 호출 ajax
		$.ajax({
			url:"../back/mapper/movieMapper.php",
			type:"post",
			data:{action:"genreList"},
			error: function(data){
				alert("에러 : " + data);
			},
			success: function(data){
				// JSON으로 날아온 값 변환
				let obj = JSON.parse(data);
				for (var i = 0; i < obj.length; i++){
				$("#searchGenre").append("<option value='"+obj[i].genreId+"'>"+obj[i].genreName+"</option>");
				}
			}
		}); // 장르 호출 ajax 종료

		// 영화 등록 버튼 클릭
		$("#insertBtn").click(function(){

			let title = $("#inputTitle").val();
			let story = $("#inputStory").val();
			let image = $("#inputImage")[0].files[0];
			let date = $("#inputDate").val();
			let director = $("#inputDirector").val();
			let genre = $("#searchGenre").val();

			let formData = new FormData();

			//이미지를 이동시키기 위한 폼데이터
			formData.append("action", "insert");
			formData.append("movieTitle", title);
			formData.append("movieStory", story);
			formData.append("movieImage", image);
			formData.append("openingDate", date);
			formData.append("movieDirector", director);
			formData.append("movieGenre", genre);
			
			$(".errMsg").css("display", "none");

			// 미입력 방지용
			if (title == "" || title == null){
				$("#inputTitle").focus();
				$(".titleMsg").css("display", "inline");
				return;
			}
			if (director == "" || director == null){
				$("#inputDirector").focus();
				$(".directorMsg").css("display", "inline");
				return;
			}
			if (story == "" || story == null){
				$("#inputStory").focus();
				$(".storyMsg").css("display", "inline");
				return;
			}
			if (image == "" || image == null){
				$("#inputImage").focus();
				$(".imageMsg").css("display", "inline");
				return;
			}
			if (date == "" || date == null){
				$("#inputDate").focus();
				$(".dateMsg").css("display", "inline");
				return;
			}

			// 영화 등록 ajax
			$.ajax({
				url:"../back/mapper/movieMapper.php",
				type:"post",
	            data:formData,
	            contentType: false,
	            processData: false,
				error : function(data){
		            alert("영화 등록 에러: " + data);
		        },
		        success : function(data){
					if ($.trim(data)== "OK"){
						alert("영화 등록 성공!");
						location.replace("../index.php");
					}
					else
						alert(data);
		        }
		    }); //영화 등록 ajax 종료
		}); // 영화 버튼 클릭 function 종료
	});*/
    