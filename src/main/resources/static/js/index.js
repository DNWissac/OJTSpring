/**
 * index.html (js)
 */

	$().ready(function() {

		// 영화 최신순 조회 버튼 클릭 시
		$("#latestList").click(function () {


			//let startNum = parseInt($("#startPageNum").val());

			// 버튼을 다시 눌렀을 때 중복으로 나오지 않게 하도록 테이블 초기화
			/*$("#input_data>thead").empty();
			$("#input_data>tbody").empty();
			$(".pagination").empty();*/

			let data = "연습";
			let messageDTO = {
				result : data
			};

			// ajax로 리스트 출력
			$.ajax ({
				type:"POST"
				, url:"/movie/list"
				, data:messageDTO
				, error : function () {
					alert('에러 발생!');
				}
				, success : function(data) {
					console.log('통신결과');
					console.log(data);

					/*
					// JSON으로 날아온 값 변환
					let obj = JSON.parse(data);
					// result 값(리스트)
					let listArr = obj['result'];
					// count 값(영화 총 개수)
					let count = obj['movieCount'];
					
					$("#input_data>thead").append("<tr>");
					$("#input_data>thead").append("<th>영화제목</th>");
					$("#input_data>thead").append("<th>감독</th>");
					$("#input_data>thead").append("<th>사진</th><th>개봉일</th>");
					$("#input_data>thead").append("</tr>");
					
					for (var i = 0; i < listArr.length; i++){
						
						$("#input_data>tbody").append("<tr>");
						$("#input_data>tbody").append("<td><a href='view/moviedetail.htmlhtml?seq="+listArr[i]['movieSeq']+"'>"+listArr[i]['movieTitle']+"</td>");
						$("#input_data>tbody").append("<td>"+listArr[i]['movieDirector']+"</td>");
						$("#input_data>tbody").append("<td><img src='"+listArr[i]['movieImage']+"' alt='사진 없음'></td>");
						$("#input_data>tbody").append("<td>"+listArr[i]['openingDate']+"</td></tr>");
					}
					*/

					// paging(count, 1);

				}
			
			})

		});
	});
/*
		// 사용자 조회 클릭 시
		$("#userList").click(function(){

			let startNum = parseInt($("#startPageNum").val());
			let endNum = parseInt($("#endPageNum").val());

			// 버튼을 다시 눌렀을 때 중복으로 나오지 않게 하도록 테이블 초기화
			$("#input_data>thead").empty();
			$("#input_data>tbody").empty();
			$("#tbl>tbody").empty();
			$(".pagination").empty();
			
			$.ajax({
				url:"back/mapper/userMapper.php",
				type:"post",
				data:{action:"list",
					startPageNum:startNum},
				success: function (data){

					// JSON으로 날아온 값 변환
					let obj = JSON.parse(data);

					let listArr = obj['result'];
					let count = obj['userCount'];

					$("#input_data>thead").append("<tr>");
					$("#input_data>thead").append("<th>이메일</th>");
					$("#input_data>thead").append("<th>닉네임</th>");
					$("#input_data>thead").append("<th>관리자여부</th>");
					$("#input_data>thead").append("</tr>");
					
					for (var i = 0; i < listArr.length; i++){
						$("#input_data>tbody").append("<tr>");
						$("#input_data>tbody").append("<td>"+listArr[i]['userEmail']+"</td>");
						$("#input_data>tbody").append("<td>"+listArr[i]['userNickName']+"</td>");

						if (listArr[i]['userAdmin'] == 1)
							$("#input_data>tbody").append("<td>관리자</td></tr>");
						else
							$("#input_data>tbody").append("<td>일반사용자</td></tr>");
					
					}

					paging(count,2);
					
				}

				
			});
			
		});

		// 초기화 버튼 클릭 시
		$("#resetList").click(function(){
			$("#input_data>thead").empty();
			$("#input_data>tbody").empty();
			$("#tbl>tbody").empty();
			$(".pagination").empty();
		});

		$("#movieInsertBtn").click(function(){
			location.href="/view/movieinsertform.htmlhtml";
		});
		
	});

	function pageMove(num, action){

		// 시작숫자와 끝 숫자 변경
		let startNum = (num-1)*10;
		let endNum = startNum+10;

		// 변경된 숫자 hidden 값으로 보관
		$("#startPageNum").val(startNum);
		$("#endPageNum").val(endNum);
		$("#nowPage").val(num);

		// 바뀐 값으로 호출
		if (parseInt(action) == 1){
			$("#latestList").trigger("click");
		}
		else if (parseInt(action) == 2){
			$("#userList").trigger("click");
		}
		
	}

	function blockMove(num, action){

		// 블록 값 변경
		$("#nowBlock").val(num);
		let blockNum = $("#nowBlock").val();

		// 변경된 숫자 hidden 값으로 보관
		$("#startPageNum").val((blockNum*5)*10);
		$("#endPageNum").val($("#startPageNum").val()+10);

		// 바뀐 값으로 호출
		if (parseInt(action) == 1){
			$("#latestList").trigger("click");
		}
		else if (parseInt(action) == 2){
			$("#userList").trigger("click");
		}
		
		
	}

	function paging(count, action){
		
		// ajax로 페이징 처리하기
		// 현재 페이지
		let page = parseInt($("#nowPage").val());
		
		// 마지막 페이지 수
		let pageNum = Math.ceil(count/10);

		// 페이지 블럭 5개씩 표기
		let block_size = 5;

		// 현재 블럭의 위치(디폴트 0) 
		let block_num = parseInt($("#nowBlock").val());

		// 현재 블럭의 맨 처음 페이지 넘버
		let block_start = (block_size * block_num) + 1;
		
		// 현재 블럭의 맨 끝 페이지 넘버 (첫 번째 블럭이라면 5)
		let block_end = block_start + (block_size - 1);
		let nowPage = parseInt($("#nowPage").val());
		let nowBlock = parseInt($("#nowBlock").val());

		if (block_num != 0)
			$(".pagination").append("<li class='page-item'><a class='page-link' href='#' onclick='blockMove("+(nowBlock-1)+", "+action+")'>◀</a></li>");

		// 페이지 개수가 블럭 마지막 페이지보다 적다면
		if (pageNum < block_end){
			for (var i = block_start; i <= pageNum; i++){
				$(".pagination").append("<li class='page-item'><a class='page-link' href='#' onclick='pageMove("+i+", "+action+")'>"+i+"</a></li>");
			}
			return;
		}
		
		else{
			for (var i = block_start; i <= block_end; i++){
				$(".pagination").append("<li class='page-item'><a class='page-link' href='#' onclick='pageMove("+i+", "+action+")'>"+i+"</a></li>");
			}
		}
		if (block_end < pageNum){
			$(".pagination").append("<li class='page-item'><a class='page-link' href='#' onclick='blockMove("+(nowBlock+1)+", "+action+")'>▶</a></li>");
		}

	}*/

	