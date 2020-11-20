package cn.xy.novelwebproject.service;

import cn.xy.novelwebproject.bean.Catalog;
import cn.xy.novelwebproject.bean.Novel;
import cn.xy.novelwebproject.bean.PageQuery;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface NovelService {
		Novel getNovelByColums (int colum, int i);

		List<Novel> getNovelByColumindex (HashMap<String, Integer> map);

		//周推榜
		List<Novel> getWeekWecommend () throws JsonProcessingException;

		//历史
		List<Novel> getHistoryList (int size);

		//网游
		List<Novel> getOnlineGameList (int size);

		//武侠
		List<Novel> getMartialArtsList (int size);

		//都市
		List<Novel> getCityList (int size);

		//仙侠
		List<Novel> getImmortalKnightList (int size);

		//科幻榜
		List<Novel> getScienceFictionList (int size);

		//玄幻榜
		List<Novel> getFantasyList (int size);

		// 军事榜
		List<Novel> getMilitaryList (int size);

		//获取总页数
		Integer getAllPage (String type);

		//获取某类型的第几页
		List<Novel> getPageByType (PageQuery query);

		//下载榜
		List<Novel> getDownloadsList (PageQuery query);

		//月票榜
		List<Novel> getMonthTicketList (PageQuery query);

		//收藏榜
		List<Novel> getCollectionList (PageQuery query);

		//分类查询
		int getClassiFicationPageSize (HashMap<String, Object> map);

		List<Novel> getClassiFicationList (HashMap<String, Object> map);

		//根据名字查询小说信息信息
		Novel getNovelMsg (String book_name);

		//获取小说目录
		List<Catalog> getBookCatalogs (String novelname);

		String getNovelCatlog (String novelname, String catlogname);

		int voteTicket (String novelname,String username);

		Map<String, List<Novel>> getRankList();
}
