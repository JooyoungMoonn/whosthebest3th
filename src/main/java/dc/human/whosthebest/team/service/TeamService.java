package dc.human.whosthebest.team.service;

import dc.human.whosthebest.vo.MemberVO;
import dc.human.whosthebest.vo.TeamInfoVO;
import dc.human.whosthebest.vo.TeamMemberVO;

import java.util.List;

public interface TeamService {
    public List listTeams() throws Exception;
    public int insertTeamInfo(TeamInfoVO teamInfoVO) throws Exception;
    public int insertTeamMember(TeamMemberVO teamMemberVO) throws Exception;
    public int createTeamAndAddMember(TeamInfoVO teamInfo, String userID) throws Exception;
    public List<TeamInfoVO> getTeamsByUserId(String userID) throws Exception;
    public TeamInfoVO getTeamInfoById(int tID) throws Exception;
    public List ranking() throws Exception;
}
