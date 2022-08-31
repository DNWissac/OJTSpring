/**
 * moviedetail.html (js)
 */
	$().ready(function() {
		// 인덱스 페이지에서 넘어온 영화번호 받기
		let nMovieSeq = $("#movieSeq").val();
		// ajax로 해당 seq 댓글 받아오기
		$.ajax({
			type:"GET"
			, url:"/movie/scorelist"
			, data:{
				nMovieSeq:nMovieSeq
			}
			, error : function(data) {
	            alert("서버 내부 에러 발생.");
				console.log(data);
	        }
	        , success : function(data) {
		        // JSON형식으로 수신한 데이터 처리
	        	let obj = JSON.parse(data);
				// 값이 제대로 넘어 왔다면
				if (obj["status"] == 200) {
					// result 값(리스트)
					let movieScoreList = obj["result"];
					// 콘솔로 결과값 출력
					console.log("status : ", obj["status"]);
					console.log("msg : ", obj["msg"]);
					console.log("result : " + movieScoreList);
					console.log("average : " + obj["average"]);
					// 영화 점수 평균
					let movieScoreAverage = obj["average"];
					$("#movieScoreAvg").text(movieScoreAverage);
					// 반복문으로 리스트 뿌리기
					for (var i = 0; i < movieScoreList.length; i++) {
						let tbody = "";
						tbody += "<tr>";
						tbody += "<th style='width: 10%;'>"+movieScoreList[i]["sUserNickName"]+"</td>";
						tbody += "<td style='width: 8%;'>"+"평점 :"+movieScoreList[i]["nScore"]+"점"+"</td>";
						tbody += "<td>"+movieScoreList[i]["sScoreComment"]+"</td>";
						tbody += "<td>"+movieScoreList[i]["dtScoreDate"]+"</td></tr>";
						$("#scoreTable>tbody").append(tbody);
					}
				}
				else {
					alert(obj["status"] + " : " + obj["msg"]);
					location.reload();
					console.log("status : ", obj["status"]);
					console.log("msg : ", obj["msg"]);
					console.log("result : ", obj["result"]);
				}
	        }
	    });// ajax 종료
	}); // $().ready(funtion()) 종료

	function deleteClick() {
		if (confirm("정말로 삭제하시겠습니까?")) {
			$.ajax({
				url:"/movie/movieDelete",
				type:"POST",
				data:{movieSeq:$("#movieSeq").val()},
				error : function() {
		            alert("에러 발생");
		        },
		        success : function(data) {
					let obj = JSON.parse(data);

					if (obj["status"] == 400) {
						alert(obj["msg"]);
					} else if (obj["status"] == 500) {
						alert(obj["msg"]);
						console.log("status : ", obj["status"]);
						console.log("exception : ", obj["exception"]);
					} else if (obj["status"] == 200) {
						alert(obj["msg"]);
						location.href="/";
					} else {
						alert("알 수 없는 오류 발생");
					}
		        }
		    }); // ajax 종료
		} // if문 종료
	}// function deleteClick() 종료
	
	function updateClick() {
		location.href="/movie/movieupdateform?movieSeq="+$("#movieSeq").val();
	}// function updataClick() 종료

