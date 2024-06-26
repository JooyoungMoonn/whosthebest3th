<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/maketeam.css">
  <title>maketeam</title>
</head>
<body>
  <header>
    <div class="logo">
      <img src="./image/logo.png">
    </div>
    <div>
      <span><a href="#">로그아웃</a>&nbsp;&nbsp;|&nbsp;</span>
      <span><a href="#">마이페이지</a></span>
    </div>
    <div>
      <ul>
        <li>
          <a href="teamlist.html">팀</a>
          <div>
            <ul>
              <li><a href="teamlist.html">팀 목록</a></li>
              <li><a href="myteam.html">나의 팀</a></li>
            </ul>
          </div>
        </li>
        <li><a href="#">경기 목록</a></li>
        <li><a href="#">경기장 목록</a></li>
        <li><a href="#">랭킹</a></li>
        <li><a href="#">게시판</a></li>
      </ul>
    </div>
  </header>
  <main style="text-align: center; height: 800px;">
    <div>　</div>
    <div>
      <div class="maketeam">
        <div>
          팀 만들기
        </div>
        <div>
          <form name="maketeam" method="post" action="/addTeam" enctype="utf-8">
            <div>
              <input type="file" name="tLogo">
            </div>
            <div>
              <input type="text" name="tName" size="12" maxlength="12" placeholder="팀 이름을 입력해주세요" required>
              <input type="button" value="팀이름 중복">
            </div>
            <div>
              <select name="tRegion">
                <option value="" disabled selected>지역</option>
                <option value="서울">서울</option>
                <option value="인천">인천</option>
                <option value="경기">경기</option>
                <option value="충남">충남</option>
                <option value="충북">충북</option>
                <option value="대전">대전</option>
                <option value="대구">대구</option>
                <option value="경북">경북</option>
                <option value="경남">경남</option>
                <option value="울산">울산</option>
                <option value="부산">부산</option>
                <option value="전남">전남</option>
                <option value="전북">전북</option>
                <option value="광주">광주</option>
                <option value="강원">강원</option>
              </select>
            </div>
            <div>
              <select name="tMinAge">
                <option value=0 disabled selected>시작나이</option>
                <option value=20>20</option>
              </select>
              <select name="tMaxAge">
                <option value=0 disabled selected>끝나이</option>
                <option value=21>21</option>
              </select>
            </div>
            <div>
              <input type="text" name="tPhone" size="12" maxlength="12" placeholder="전화번호">
            </div>
            <div>
              <input type="text" name="tInfo" size="20" maxlength="20" placeholder="팀 소개">
            </div>
            <div>
              <a href="myteam.html"> <input type="submit" value="팀 만들기"> </a>
              <!--submit또는 버튼 태그로 변경해야됨-->
              <input type="reset" value="취소">
              <!--reset또는 버튼 태그로 변경해야됨-->
            </div>
          </form>
        </div>
      </div>
    </div>
  </main>


  <!--footer-->
  <footer>
    <div>
      <ul>
        <li><a href="#">이용약관</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
        <li><a href="#">개인정보처리방침</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
        <li><a href="#">오류/건의</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
        <li><a href="#">광고/후원문의</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
        <li><a href="#">고객센터</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
        <li><a href="#">&copy;누가잘차</a></li>
      </ul>
    </div>
    <div>&copy;2024.MunjuGangz All rights reserved.</div>
  </footer>
</body>
</html>