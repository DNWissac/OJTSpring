/**
 * index.html (js)
 */

	$().ready(function() {

		/**
		 * 영화 최신순 조회 버튼 클릭
		 */
		$("#latestList").click(function () {
			
			// 페이징처리를 위해서 페이지의 시작값 저장
			let startNum = parseInt($("#startPageNum").val());

			// 버튼을 다시 눌렀을 때 중복으로 나오지 않게 하도록 테이블 초기화
			$("#input_data>thead").empty();
			$("#input_data>tbody").empty();
			$(".pagination").empty();

			/**
			 * ajax로 리스트 출력
			 */
			$.ajax({
				type:"GET"
				, url:"/movie/list"
				, data:{startNum : startNum}
				, error : function (data) {
					alert("서버 내부 에러 발생.");
					console.log(data);
				}
				, success : function(data) {
					// JSON으로 날아온 값 변환
					let obj = JSON.parse(data);
					// 만약 정상적으로 데이터 송수신이 완료되었다면
					if (obj["status"] == 200) {
						// result 값(리스트)
						let movieList = obj["result"];
						// 콘솔로 결과값 출력
						console.log("status : ", obj["status"]);
						console.log("msg : ", obj["msg"]);
						console.log("result : " + movieList);
						console.log("count : " + obj["count"]);
						// count 값(영화 총 개수)
						let count = obj["count"];
						// 테이블 위 th행 추가
						$("#input_data>thead").append("<tr><th>영화제목</th><th>감독</th><th>사진</th><th>개봉일</th></tr>");
						// 리스트 for문으로 돌면서 출력
						for (var i = 0; i < movieList.length; i++) {
							let tBody = "";
							tBody += "<tr>";
							tBody += "<td><a href='movie/moviedetail?movieSeq="+movieList[i]['nMovieSeq']+"'>"+XSSCheck(movieList[i]['sMovieTitle'], 1)+"</td>";
							tBody += "<td>"+XSSCheck(movieList[i]['sMovieDirector'], 1)+"</td>"
							tBody += "<td><img src='"+movieList[i]['sMovieImage']+"' alt='사진 없음'></td>"
							tBody += "<td>"+XSSCheck(movieList[i]['dtOpeningDate'], 1)+"</td></tr>"
							$("#input_data>tbody").append(tBody);
						}
						paging(count, 1);
					}
					// 그게 아니라면
					else {
						alert(obj["status"] + " : " + obj["msg"]);
						location.reload();
						console.log("status : ", obj["status"]);
						console.log("msg : ", obj["msg"]);
						console.log("result : ", obj["result"]);
					} // if~else 문 종료
				} // ajax:success 종료
			}) // ajax 종료
		}); // #latestlist click 이벤트 종료

		// 초기화 버튼 클릭 시
		$("#resetList").click(function(){
			$("#input_data>thead").empty();
			$("#input_data>tbody").empty();
			$("#tbl>tbody").empty();
			$(".pagination").empty();
		}); // #resetList click 이벤트 종료

		$("#movieInsertBtn").click(function(){
			location.href="/movieinsertform";
		}); // #movieInsertBtn click 이벤트 종료
		
	}); // jquery 종료

	/**
	 * 페이지 이동
	 * @param num 바꿀 페이지 번호
	 * @param action 어떤 리스트인지( 1: 영화 리스트, 2: 회원 리스트 )
	 */
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
	}

	/**
	 * 블록 이동
	 * @param num 바꿀 블록의 번호
	 * @param action 어떤 리스트인지( 1: 영화 리스트, 2: 회원 리스트 )
	 */
	function blockMove(num, action){

		// 블록 값 변경
		$("#nowBlock").val(num);
		let blockNum = $("#nowBlock").val();

		// 변경된 숫자 hidden 값으로 보관
		$("#startPageNum").val((blockNum*5)*10);
		$("#endPageNum").val($("#startPageNum").val()+10);
		$("#nowPage").val(blockNum * 5 + 1);

		// 바뀐 값으로 호출
		if (parseInt(action) == 1){
			$("#latestList").trigger("click");
		}
	}

	/**
	 * 페이징 처리
	 * @param count	게시물 총 개수
	 * @param action 어떤 리스트인지( 1: 영화 리스트, 2: 회원 리스트 )
	 */
	function paging(count, action){

			// ajax로 페이징 처리하기
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

			// 첫 번째 블록 (1페이지 ~ 5페이지)이라면 이전 블록으로 가기가 나타나지 않도록
			if (block_num != 0)
				$(".pagination").append("<li class='page-item'><a class='page-link' href='#' onclick='blockMove("+(nowBlock-1)+", "+action+")'>◀</a></li>");

			// 페이지 개수가 블럭 마지막 페이지보다 적다면 더 이상 나타나지 않도록
			if (pageNum < block_end){
				for (var i = block_start; i <= pageNum; i++){
					if (nowPage == i) {
						$(".pagination").append("<li class='page-item active'><a class='page-link' href='#'>"+i+"</a></li>");
					} else {
						$(".pagination").append("<li class='page-item'><a class='page-link' href='#' onclick='pageMove("+i+", "+action+")'>"+i+"</a></li>");
					}
				}
				return;
			}
			else{
				for (var i = block_start; i <= block_end; i++){
					if (nowPage == i) {
						$(".pagination").append("<li class='page-item active'><a class='page-link' href='#'>"+i+"</a></li>");
					} else {
						$(".pagination").append("<li class='page-item'><a class='page-link' href='#' onclick='pageMove("+i+", "+action+")'>"+i+"</a></li>");
					}
				}
			}
			if (block_end < pageNum){
				$(".pagination").append("<li class='page-item'><a class='page-link' href='#' onclick='blockMove("+(nowBlock+1)+", "+action+")'>▶</a></li>");
			}

		}

	/**
	 * XSS 방지
	 * @param str 확인할 string
	 * @param level 적용 방식
	 * @returns {*} 바뀌어진 string
	 * @constructor
	 */
	function XSSCheck(str, level) {
		if (level == undefined || level == 0) {
			str = str.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|\-/g,"");
		} else if (level != undefined && level == 1) {
			str = str.replace(/\</g, "&lt;");
			str = str.replace(/\>/g, "&gt;");
		}
		return str;
	}


	