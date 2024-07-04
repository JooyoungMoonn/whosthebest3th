package dc.human.whosthebest.team.controller;

import dc.human.whosthebest.team.dao.TeamDAO;
import dc.human.whosthebest.team.service.TeamService;
import dc.human.whosthebest.team.service.TeamServiceImpl;
import dc.human.whosthebest.vo.GameListVO;
import dc.human.whosthebest.vo.TeamInfoVO;
import dc.human.whosthebest.vo.TeamMemberVO;
import dc.human.whosthebest.vo.TeamProfileVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("teamController")
public class TeamControllerImpl implements TeamController{

    @Autowired
    private TeamService teamService;


    //팀 만들기 페이지 단순 매핑
    @Override
    @GetMapping("/teamMake")
    public String teamMakePage() {
        return "/team/teamMake";
    }

    //나의팀 페이지
    @Override
    @GetMapping("/myTeam")
    public ModelAndView myTeamPage() {
        ModelAndView mav = new ModelAndView("/team/myTeam");
        String userID = "heo"; // 실제 사용자 ID 가져오는 로직으로 대체

        try {
            List<TeamInfoVO> myTeams = teamService.getTeamsByUserId(userID);
            mav.addObject("myTeams", myTeams);

            if(myTeams != null && !myTeams.isEmpty()) {
                int tID=myTeams.get(0).gettID();
                TeamProfileVO teamProfile = teamService.getTeamProfile(tID);
                List<GameListVO> gameListVO = teamService.selectGameSchedule(tID);
                mav.addObject("teamProfile", teamProfile);
                mav.addObject("gameList", gameListVO);
            } else {
                mav.addObject("teamProfile",null);
            }
        } catch (Exception e) {
            mav.addObject("errorMsg", "팀 목록을 가져오는 도중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return mav;
    }

    //특정 팀의 상세 정보 반환
    @GetMapping("/api/teamInfo")
    @ResponseBody
    public TeamProfileVO getTeamInfo(@RequestParam("tID") int tID) {
        try {
            return teamService.getTeamProfile(tID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    };

    //팀 만들기 insert
    //팀원 목록에 추가하는 것과 동시에 시행
        //해당지역 이미 생성되있는 팀이름 검사 필요
        //가입한 팀 3개 검사 필요
        //팀 만들때 팀장도 team_member 테이블에 저장 필요
    @Override
    @RequestMapping(value="/insertTeamInfo" ,method = RequestMethod.POST)
    public ModelAndView insertTeamInfo(@ModelAttribute("teamInfo") TeamInfoVO teamInfo) throws Exception {
        ModelAndView mav = new ModelAndView();
        String userID = "HONG";
        //실제 userID 가져오는 로직으로 대체
//        String userID = request.getSession().getAttribute("userID").toString();
        try {
            //팀생성과 동시에 팀원 목록에도 팀장 추가
            int result = teamService.createTeamAndAddMember(teamInfo, userID);
            String viewName = "";

            if (result < 1) {
                mav.addObject("errorMsg", "정상적으로 입력이 되지 않았습니다");
                viewName = "team/teamList";
            } else {
                viewName = "redirect:/teamList";
            }
            mav.setViewName(viewName);
        } catch (Exception ex) {
            mav.addObject("errorMsg", "정상적으로 입력이 되지 않았습니다");
            mav.setViewName("team/teamList");
            System.out.println(ex.getMessage());
        }
        return mav;
        //최소나이 최대나이 처리 필요
        //중복 기능 따로 만들기
    }

    //팀 가입하기(유저)
        //이미 가입한 팀 검사
        //3개이상 가입 금지
    @Override
    @RequestMapping(value="/insertTeamMember" ,method = RequestMethod.POST)
    public ModelAndView insertTeamMember(@RequestParam("tID") int tID) throws Exception{
        ModelAndView mav = new ModelAndView();

        try {
            String viewName = "";
            String userID = "MOON";
            TeamMemberVO teamMember = new TeamMemberVO();
            teamMember.setCreatedID(userID);
            teamMember.setuID(userID);
            teamMember.settID(tID);
            int insertTeamMemberResult = teamService.insertTeamMember(teamMember);


            if(insertTeamMemberResult<1) {
                mav.addObject("errorMsg", "팀 가입 실패");
                viewName = "team/teamList";
            } else {
                viewName = "redirect:/teamList";
                //redirect 수정 핋요
            }
            mav.setViewName(viewName);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return mav;
    }

    //팀목록 페이지 매핑
        //페이징 처리 필요
        //검색 기능 지역 필터링 메소드 추가 필요
    @Override
    @GetMapping("/teamList")
    public ModelAndView listTeams() throws Exception{
        List teamsList = teamService.listTeams();
        ModelAndView mav = new ModelAndView("/team/teamList");
        mav.addObject("teamsList", teamsList);
        return mav;
    }



    //랭킹 페이지 매핑
        //페이징 처리 필요
        //검색 기능 지역 필터링 메소드 추가 필요
    @Override
    @GetMapping("/ranking")
    public ModelAndView Ranking() throws Exception{
        List ranking = teamService.ranking();
        ModelAndView mav = new ModelAndView("/team/ranking");
        mav.addObject("ranking", ranking);
        return mav;
    }



}
