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
		
		// 기본정보 호출 ajax
		$.ajax({
			url:"../back/mapper/movieMapper.php",
			type:"post",
			data:{
				movieSeq:movieSeq,
				action:"search"
				},
			error : function(){
	            alert("에러");
	        },
	        success : function(data){
		        // JSON형식으로 수신한 데이터 처리
	        	let obj = JSON.parse(data);
				// result 값(리스트)
				let detailArr = obj["result"][0];
				$("#inputTitle").val(detailArr["movieTitle"]);
				$("#inputDirector").val(detailArr["movieDirector"]);
				$("#searchGenre").val(detailArr["genreId"]);
				$("#inputStory").val(detailArr["movieStory"]);
				// $("#inputImage").val(detailArr["movieImage"]);
				$("#inputDate").val(detailArr["openingDate"]);
	        }

	    }); // ajax 종료

		
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

		// 영화 수정 버튼 클릭
		$("#updateBtn").click(function(){

			let title = $("#inputTitle").val();
			let story = $("#inputStory").val();
			let date = $("#inputDate").val();
			let director = $("#inputDirector").val();
			let genre = $("#searchGenre").val();
			
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
				url:"../back/mapper/movieMapper.php",
				type:"post",
	            data:{
	            		action:"update", 
	            	  	movieTitle:title,
	                    movieStory:story,
	                    openingDate:date,
	                    movieDirector:director,
	                    movieGenre:genre,
	                    movieSeq:movieSeq
	            },
				error : function(data){
		            alert("영화 등록 에러: " + data);
		        },
		        success : function(data){
					if ($.trim(data)== "OK"){
						alert("영화 수정 성공!");
						location.replace("../index.php");
					}
					else
						alert(data);
		        }
		    }); //영화 수정 ajax 종료
		}); // 영화 버튼 클릭 function 종료
	});
    