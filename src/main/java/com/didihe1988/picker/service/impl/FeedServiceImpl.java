package com.didihe1988.picker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didihe1988.picker.common.Constant;
import com.didihe1988.picker.common.DaoOrder;
import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.dao.AttachmentDao;
import com.didihe1988.picker.dao.BookDao;
import com.didihe1988.picker.dao.FavoriteDao;
import com.didihe1988.picker.dao.FeedDao;
import com.didihe1988.picker.dao.FollowDao;
import com.didihe1988.picker.dao.RelatedImageDao;
import com.didihe1988.picker.dao.UserDao;
import com.didihe1988.picker.model.ChapterRange;
import com.didihe1988.picker.model.Favorite;
import com.didihe1988.picker.model.Feed;
import com.didihe1988.picker.model.Follow;
import com.didihe1988.picker.model.RelatedImage;
import com.didihe1988.picker.model.display.AttachmentFeedDp;
import com.didihe1988.picker.model.display.FeedDp;
import com.didihe1988.picker.model.json.NoteJson;
import com.didihe1988.picker.model.json.QuestionJson;
import com.didihe1988.picker.service.FeedService;
import com.didihe1988.picker.service.support.feed.FeedDpGenerator;
import com.didihe1988.picker.service.support.feed.NoteDpGenerator;
import com.didihe1988.picker.service.support.feed.QuestoinDpGenerator;
import com.didihe1988.picker.utils.DateUtils;

@Service
@Transactional
public class FeedServiceImpl implements FeedService {

	@Autowired
	private FeedDao feedDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BookDao bookDao;

	@Autowired
	private FollowDao followDao;

	@Autowired
	private FavoriteDao favoriteDao;

	@Autowired
	private RelatedImageDao relatedImageDao;

	@Autowired
	private AttachmentDao attachmentDao;

	@Autowired
	@Qualifier("questionDpGenerator")
	private FeedDpGenerator questionDpGenerator;

	@Autowired
	@Qualifier("noteDpGenerator")
	private FeedDpGenerator noteDpGenerator;

	@Override
	public boolean checkOperateValidation(int userId, int feedId) {
		// TODO Auto-generated method stub
		return feedDao.checkOperateValidation(userId, feedId);
	}

	@Override
	public Feed getFeedById(int id) {
		// TODO Auto-generated method stub
		return feedDao.queryModelById(id);
	}

	@Override
	public int addFeed(Feed feed) {
		// TODO Auto-generated method stub
		if (feed == null) {
			return Status.NULLPOINTER;
		}
		int status = feedDao.addModel(feed);
		if (status == -1) {
			return Status.EXISTS;
		}

		//related num ++
		//需要重构 
		if(feed.getType()==Feed.TYPE_QUESTION)
		{
			userDao.incrementNum(Constant.QUESTION_NUM, feed.getUserId());
			bookDao.incrementNum(Constant.QUESTION_NUM, feed.getBookId());
		}
		else if(feed.getType()==Feed.TYPE_NOTE)
		{
			userDao.incrementNum(Constant.NOTE_NUM, feed.getUserId());
			bookDao.incrementNum(Constant.NOTE_NUM, feed.getBookId());
		}
		else if(feed.getType()==Feed.TYPE_ATTACHMENT_FEED)
		{
			//User还没有AttachmentNum(上传过的附件)，先不写了
			bookDao.incrementNum(Constant.ATTACH_FEED_NUM, feed.getBookId());
		}
		return Status.SUCCESS;
	}

	@Override
	public int deleteFeed(Feed feed, int userId) {
		// TODO Auto-generated method stub
		if (feed == null) {
			return Status.NULLPOINTER;
		}
		if (!feedDao.checkOperateValidation(userId, feed.getId())) {
			return Status.INVALID;
		}
		feedDao.deleteFeed(feed);
		//related num --
		return Status.SUCCESS;
	}

	@Override
	public int deleteFeedById(int feedId, int userId) {
		// TODO Auto-generated method stub
		if (!feedDao.checkOperateValidation(userId, feedId)) {
			return Status.INVALID;
		}
		feedDao.deleteModelById(feedId);
		return Status.SUCCESS;
	}

	@Override
	public int updateFeed(Feed feed, int userId) {
		// TODO Auto-generated method stub
		if (feed == null) {
			return Status.NULLPOINTER;
		}
		if (!feedDao.checkOperateValidation(userId, feed.getId())) {
			return Status.INVALID;
		}
		int status = feedDao.updateModel(feed);
		if (status == -1) {
			return Status.NOT_EXISTS;
		}
		return Status.SUCCESS;
	}

	@Override
	public List<Feed> getFeedListByBookId(int bookId, int type) {
		// TODO Auto-generated method stub
		return feedDao.queryFeedListByBookId(bookId, type);
	}

	@Override
	public List<Feed> getFeedListByUserId(int userId, int type) {
		// TODO Auto-generated method stub
		return feedDao.queryFeedListByUserId(userId, type);
	}

	@Override
	public int getLatestFeedByBookId(int bookId, int type) {
		// TODO Auto-generated method stub
		return feedDao.getLatestFeedByBookId(bookId, type);
	}

	@Override
	public boolean isFeedExistsById(int id) {
		// TODO Auto-generated method stub
		return feedDao.isModelExistsById(id);
	}

	@Override
	public FeedDp getFeedDpByFeedId(int id, int curUserId) {
		// TODO Auto-generated method stub
		Feed feed = getFeedById(id);
		return getFeedDpByFeed(getGeneratorByType(feed.getType()), feed,
				curUserId);
	}

	@Override
	public AttachmentFeedDp getAttFeedDpByFeedId(int id) {
		// TODO Auto-generated method stub
		AttachmentFeedDp attFeedDp = feedDao.queryAttFeedDpByFeedId(id);
		setAttachments(attFeedDp);
		return attFeedDp;
	}

	/*
	 * 整个list已经确定了类型、不应该在FeedDp中switch 因此把FeedDp生成的过程抽成一个类
	 */
	private FeedDp getFeedDpByFeed(FeedDpGenerator generator, Feed feed,
			int curUserId) {
		return generator.getFeedDpfromFeed(feed, curUserId);
	}

	private FeedDpGenerator getGeneratorByType(int type) {
		if (type == Feed.TYPE_QUESTION) {
			return this.questionDpGenerator;
		} else {
			return this.noteDpGenerator;
		}
	}

	private List<FeedDp> getFeedDpList(List<Feed> feedList, int curUserId) {
		List<FeedDp> list = new ArrayList<FeedDp>();
		FeedDpGenerator generator = null;
		if (feedList.size() > 0) {
			generator = getGeneratorByType(feedList.get(0).getType());
		}
		for (Feed feed : feedList) {
			FeedDp feedDp = getFeedDpByFeed(generator, feed, curUserId);
			list.add(feedDp);
		}
		return list;
	}

	/*
	 * private List<String> getImageUrlsFromFeed(Feed feed) { return
	 * relatedImageDao.queryImageUrlsByKey(feed.getId(),
	 * RelatedImage.FEED_IMAGE); }
	 */

	@Override
	public List<FeedDp> getFeedDpListByUserId(int userId, int type,
			int curUserId) {
		// TODO Auto-generated method stub
		List<FeedDp> list = feedDao.queryFeedDpListByUserId(userId, type);
		FeedDpGenerator generator = null;
		if (list.size() > 0) {
			generator = getGeneratorByType(list.get(0).getType());
		}
		for (FeedDp feedDp : list) {
			generator.completeFeedDp(feedDp, curUserId);
		}
		return list;
	}

	@Override
	public List<FeedDp> getFeedDpListByBookId(int bookId, int type,
			int curUserId) {
		// TODO Auto-generated method stub
		List<FeedDp> list = feedDao.queryFeedDpListByBookId(bookId, type);
		FeedDpGenerator generator = null;
		if (list.size() > 0) {
			generator = getGeneratorByType(list.get(0).getType());
		}
		for (FeedDp feedDp : list) {
			generator.completeFeedDp(feedDp, curUserId);
		}
		return list;
	}

	@Override
	public List<FeedDp> getFeedDpListByBookId(int bookId, int type,
			int curUserId, DaoOrder order) {
		// TODO Auto-generated method stub
		List<FeedDp> list = feedDao
				.queryFeedDpListByBookId(bookId, type, order);
		FeedDpGenerator generator = null;
		if (list.size() > 0) {
			generator = getGeneratorByType(list.get(0).getType());
		}
		for (FeedDp feedDp : list) {
			generator.completeFeedDp(feedDp, curUserId);
		}
		return list;
	}

	@Override
	public List<FeedDp> getLimitedFeedDpListByBookId(int bookId, int type,
			int curUserId, int limit) {
		// TODO Auto-generated method stub
		List<FeedDp> list = feedDao.queryLimitedFeedDpListByBookId(bookId,
				type, limit);
		System.out.println("list: " + list);
		FeedDpGenerator generator = null;
		if (list.size() > 0) {
			generator = getGeneratorByType(list.get(0).getType());
		}
		for (FeedDp feedDp : list) {
			generator.completeFeedDp(feedDp, curUserId);
		}
		return list;
	}

	@Override
	public List<FeedDp> getLimitedFeedDpListByBookId(int bookId, int type,
			int curUserId, int limit, DaoOrder order) {
		// TODO Auto-generated method stub
		List<FeedDp> list = feedDao.queryLimitedFeedDpListByBookId(bookId,
				type, limit, order);
		FeedDpGenerator generator = null;
		if (list.size() > 0) {
			generator = getGeneratorByType(list.get(0).getType());
		}
		for (FeedDp feedDp : list) {
			generator.completeFeedDp(feedDp, curUserId);
		}
		return list;
	}

	@Override
	public List<FeedDp> getLimitedFeedDpsByBookId(int bookId, int curUserId,
			int limit, DaoOrder order) {
		// TODO Auto-generated method stub
		List<FeedDp> list = feedDao.queryLimitedFeedDpsByBookId(bookId, limit,
				order);
		for (FeedDp feedDp : list) {
			if (feedDp.getType() == Feed.TYPE_QUESTION) {
				questionDpGenerator.completeFeedDp(feedDp, curUserId);
			} else if (feedDp.getType() == Feed.TYPE_NOTE) {
				noteDpGenerator.completeFeedDp(feedDp, curUserId);
			}
		}
		return list;
	}

	@Override
	public List<Feed> getLimitedFeedListByBookId(int bookId, int type, int limit) {
		// TODO Auto-generated method stub
		return feedDao.queryLimitedFeedListByBookId(bookId, type, limit);
	}

	@Override
	public List<Feed> getFeedListForBrowse(int bookId) {
		// TODO Auto-generated method stub
		return feedDao.queryFeedListForBrowse(bookId);
	}

	@Override
	public List<FeedDp> getFeedDpListForBrowse(int bookId, int curUserId) {
		// TODO Auto-generated method stub
		List<FeedDp> list = feedDao.queryFeedDpListForBrowse(bookId);
		for (FeedDp feedDp : list) {
			completeFeedDp(feedDp, curUserId);
		}
		return list;
	}

	private void completeFeedDp(FeedDp feedDp, int curUserId) {
		boolean isFollow = false, isFavorite = false;
		if (feedDp.getType() == Feed.TYPE_QUESTION) {
			isFavorite = favoriteDao.isFavoriteExistsByKey(curUserId,
					feedDp.getId(), Favorite.FAVORITE_QUESTION);
			isFollow = followDao.isFollowExistsByKey(Follow.FOLLOW_QUESTION,
					curUserId, feedDp.getId());

		} else {
			isFavorite = favoriteDao.isFavoriteExistsByKey(curUserId,
					feedDp.getId(), Favorite.FAVORITE_NOTE);
		}
		feedDp.setFollow(isFollow);
		feedDp.setFavorite(isFavorite);
	}

	@Override
	public List<FeedDp> search(String string, int type, int curUserId,boolean isLimited) {
		// TODO Auto-generated method stub
		return getFeedDpList(feedDao.search(string, type,isLimited), curUserId);
	}

	@Override
	public List<Feed> search(String string, int type,boolean isLimited) {
		// TODO Auto-generated method stub
		return feedDao.search(string, type,isLimited);
	}

	@Override
	public List<NoteJson> getNoteJsons(int userId, int page) {
		// TODO Auto-generated method stub
		List<Feed> noteList = feedDao.queryFeedListByUserId(userId,
				Feed.TYPE_NOTE, page);
		List<NoteJson> list = new ArrayList<NoteJson>();
		for (Feed feed : noteList) {
			String imageUrl = relatedImageDao.queryFirstImageUrlByKey(
					feed.getId(), RelatedImage.FEED_IMAGE);
			NoteJson noteJson = new NoteJson(feed.getTitle(), imageUrl,
					"/detail/" + feed.getId(),
					DateUtils.toDate(feed.getDate()), feed.getContent());
			list.add(noteJson);
		}
		return list;
	}

	@Override
	public List<QuestionJson> getQuestoinJsons(int userId, int page) {
		// TODO Auto-generated method stub
		List<Feed> questionList = feedDao.queryFeedListByUserId(userId,
				Feed.TYPE_QUESTION, page);
		List<QuestionJson> list = new ArrayList<QuestionJson>();
		for (Feed feed : questionList) {
			String imageUrl = relatedImageDao.queryFirstImageUrlByKey(
					feed.getId(), RelatedImage.FEED_IMAGE);
			QuestionJson questionJson = new QuestionJson(feed.getTitle(),
					imageUrl, "/detail/" + feed.getId(), DateUtils.toDate(feed
							.getDate()), feed.getCommentNum(),
					feed.getAnswerNum());
			list.add(questionJson);
		}
		return list;
	}

	@Override
	public List<Feed> getFeedListByPage(int bookId, int page) {
		// TODO Auto-generated method stub
		return feedDao.queryModelListByPage(bookId, page);
	}

	@Override
	public List<Feed> getFeedListByChapterRange(int bookId,
			ChapterRange chapterRange) {
		// TODO Auto-generated method stub
		return feedDao.queryModelListBetweenPage(bookId,
				chapterRange.getStartPage(), chapterRange.getEndPage());
	}

	@Override
	public List<AttachmentFeedDp> getAttFeedDpsByBookId(int bookId) {
		// TODO Auto-generated method stub
		List<AttachmentFeedDp> attFeedDps = feedDao.queryAttFeedDpsByBookId(
				bookId, DaoOrder.FeedIdOrder);
		for (AttachmentFeedDp attFeedDp : attFeedDps) {
			setAttachments(attFeedDp);
		}
		return attFeedDps;
	}

	@Override
	public List<AttachmentFeedDp> getAttFeedDpsByBookId(int bookId,
			DaoOrder order) {
		// TODO Auto-generated method stub
		List<AttachmentFeedDp> attFeedDps = feedDao.queryAttFeedDpsByBookId(
				bookId, order);
		for (AttachmentFeedDp attFeedDp : attFeedDps) {
			setAttachments(attFeedDp);
		}
		return attFeedDps;
	}

	@Override
	public List<AttachmentFeedDp> getLimitedAttFeedDpsByBookId(int bookId,
			int limit) {
		// TODO Auto-generated method stub
		List<AttachmentFeedDp> attFeedDps = feedDao
				.queryLimitedAttFeedDpsByBookId(bookId, limit,
						DaoOrder.FeedIdOrder);
		for (AttachmentFeedDp attFeedDp : attFeedDps) {
			setAttachments(attFeedDp);
		}
		return attFeedDps;
	}

	@Override
	public List<AttachmentFeedDp> getLimitedAttFeedDpsByBookId(int bookId,
			int limit, DaoOrder order) {
		// TODO Auto-generated method stub
		List<AttachmentFeedDp> attFeedDps = feedDao
				.queryLimitedAttFeedDpsByBookId(bookId, limit, order);
		for (AttachmentFeedDp attFeedDp : attFeedDps) {
			setAttachments(attFeedDp);
		}
		return attFeedDps;
	}

	private void setAttachments(AttachmentFeedDp attFeedDp) {
		attFeedDp.setAttachments(attachmentDao.queryAttachments(attFeedDp
				.getId()));
	}

}
