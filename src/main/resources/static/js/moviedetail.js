/**
 * moviedetail.html (js)
 */
	$().ready(function() {
		// 인덱스 페이지에서 넘어온 영화번호 받기
		let movieSeq = $("#movieSeq").val();

		// ajax로 해당 seq 영화정보 받아오기
		$.ajax({
			url:"../back/mapper/movieMapper.php",
			type:"post",
			data: {
				movieSeq:movieSeq,
				action:"search"
			},
			error : function() {
	            alert("에러");
	        },
	        success : function(data) {
		        // JSON형식으로 수신한 데이터 처리
	        	let obj = JSON.parse(data);
				// result 값(리스트)
				let detailArr = obj["result"][0];
				$(".movieImage").append("<img src='../"+detailArr["movieImage"]+"' class='rounded' alt='사진 없음'>");
				$(".movieTitle").append("<h1>"+detailArr["movieTitle"]+"</h1>");
				$(".movieStory").append(detailArr["movieStory"]);
				$(".movieScoreAvg").append(detailArr["score"]);
	        }

	    }); // ajax 종료

		// ajax로 해당 seq 댓글 받아오기
		$.ajax( {
			url:"../back/mapper/movieMapper.php",
			type:"post",
			data:{
				movieSeq:movieSeq,
				action:"scoreList"
			},
			error : function() {
	            alert("에러");
	        },
	        success : function(data) {
		        // JSON형식으로 수신한 데이터 처리
	        	let obj = JSON.parse(data);
				// result 값(리스트)
				let scoreArr = obj["result"];

				for (var i = 0; i < scoreArr.length; i++){
					$(".scoreTable>tbody").append("<tr>");
					$(".scoreTable>tbody").append("<th style='width: 10%;'>"+scoreArr[i]["nickName"]+"</td>");
					$(".scoreTable>tbody").append("<td style='width: 8%;'>"+"평점 :"+scoreArr[i]["score"]+"점"+"</td>");
					$(".scoreTable>tbody").append("<td>"+scoreArr[i]["scoreComment"]+"</td>");
					$(".scoreTable>tbody").append("<td>"+scoreArr[i]["scoreDate"]+"</td>");
					$(".scoreTable>tbody").append("</tr>");

				}					
				
	        }

	    }); // ajax 종료

		$("#inputScore").click(function() {
			let score = $("input[name=scoreRadio]:checked").val();
			let comment = $("#inputComment").val();
			
			$.ajax({
				url:"../back/mapper/movieMapper.php",
				type:"post",
	            data:{action : "scoreInsert",
		             movieScore : score,
		             scoreComment : comment,
		             movieSeq : movieSeq},
				error : function(){
		            alert("평가 등록 에러: ");
		        },
		        success : function(data){
					let jsonObj = JSON.parse(data);
					
					if(jsonObj["status"] != 200)
						alert(jsonObj["msg"]);
					else {
						alert("평가 등록 성공!");
						location.reload();
					}
		        }
		    }); //점수 등록 ajax 종료
		})

	}); // $().ready(funtion()) 종료

		

	function deleteClick() {
		if (confirm("정말로 삭제하시겠습니까?")) {
			$.ajax( {
				url:"../back/mapper/movieMapper.php",
				type:"post",
				data:{
					movieSeq:$("#movieSeq").val(),
					action:"delete"
					},
				error : function(data){
		            alert('에러 : '+data);
		        },
		        success : function(data){
			        alert(data);
		        	location.href="../resources/templates/index.html";
		        }
		    }); // ajax 종료
		} // if문 종료
	}// function deleteClick() 종료
	
	function updateClick() {
		location.href="../view/movieupdateform.html?seq="+$("#movieSeq").val();
	}// function updataClick() 종료

