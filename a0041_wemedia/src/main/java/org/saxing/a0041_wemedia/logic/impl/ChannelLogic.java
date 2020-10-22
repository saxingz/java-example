package org.saxing.a0041_wemedia.logic.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.saxing.a0041_wemedia.domain.entity.ChannelDO;
import org.saxing.a0041_wemedia.domain.entity.VideoDO;
import org.saxing.a0041_wemedia.domain.enums.DownLoadStatus;
import org.saxing.a0041_wemedia.domain.enums.Platform;
import org.saxing.a0041_wemedia.logic.IVideoLogic;
import org.saxing.a0041_wemedia.mapper.ChannelMapper;
import org.saxing.a0041_wemedia.logic.IChannelLogic;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.saxing.a0041_wemedia.platform.youtube.YouTubeApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 频道名称 服务实现类
 * </p>
 *
 * @author saxing
 * @since 2020-10-22
 */
@Service
@CommonsLog
public class ChannelLogic extends ServiceImpl<ChannelMapper, ChannelDO> implements IChannelLogic {

    @Autowired
    private IVideoLogic videoLogic;

    @Override
    public void scanChannel(DateTime startDate, DateTime endDate, String channelId) {
        ChannelDO channelDO = this.lambdaQuery().ge(ChannelDO::getChannelId, channelId).one();
        if (Objects.isNull(channelDO)) {
            log.error("没有本频道： channelId: " + channelId);
            return;
        }
        List<SearchResult> searchResults = scanResult(startDate, endDate, channelId);
        if (CollectionUtils.isEmpty(searchResults)) {
            return;
        }
        searchResults.forEach(searchResult -> {
            String kind = searchResult.getId().getKind();
            if (!Objects.equals(kind, "youtube#video")) {
                return;
            }
            log.info("找到一个youtube视频   " + searchResult);
            String videoId = searchResult.getId().getVideoId();
            VideoDO video = videoLogic.lambdaQuery().eq(VideoDO::getVideoId, videoId).one();
            if (Objects.nonNull(video)) {
                return;
            }
            String channelTitle = searchResult.getSnippet().getChannelTitle();
            String videoTitle = searchResult.getSnippet().getTitle();
            String description = searchResult.getSnippet().getDescription();
            com.google.api.client.util.DateTime publishedAt = searchResult.getSnippet().getPublishedAt();
            String thumbnails = searchResult.getSnippet().getThumbnails().getHigh().getUrl();


            video = new VideoDO().setPlatform(Platform.YOUTUBE.getName())
                    .setChannelId(channelId)
                    .setChannelTitle(channelTitle)
                    .setVideoId(videoId)
                    .setVideoTitle(videoTitle)
                    .setDescription(description)
                    .setPublishTime(new Date(publishedAt.getValue()))
                    .setThumbnails(thumbnails);
            videoLogic.saveOrUpdate(video);
        });
        // 更新channel最后follow时间
        com.google.api.client.util.DateTime lastPublishedAt = searchResults.get(searchResults.size() - 1).getSnippet().getPublishedAt();
        channelDO.setFollowTime(new Date(lastPublishedAt.getValue()));
        this.updateById(channelDO);
    }

    private List<SearchResult> scanResult(DateTime startDate, DateTime endDate, String channelId){
        List<SearchResult> result = new ArrayList<>();
        String nextPageToken = "";
        do {
            SearchListResponse searchListResponse = doSearch(startDate, endDate, channelId, nextPageToken);
            if (Objects.nonNull(searchListResponse)){
                nextPageToken = searchListResponse.getNextPageToken();
                result.addAll(searchListResponse.getItems());
            } else {
                nextPageToken = "";
            }
            try {
                Thread.sleep(new Random().nextInt(1000) + 500);
            } catch (InterruptedException ignored) {}
        } while (StringUtils.isNotEmpty(nextPageToken));
        // 根据日期排序
        result = result.stream().sorted(Comparator.comparing(o -> o.getSnippet().getPublishedAt().getValue()))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 真实搜索
     * @return response
     */
    private SearchListResponse doSearch(DateTime startDate, DateTime endDate, String channelId, String nextPageToken){
        try {
            YouTube youtubeService = YouTubeApi.youTube;
            YouTube.Search.List request = youtubeService.search()
                    .list("snippet,id");
            SearchListResponse response = request.setChannelId(channelId)
                    .setMaxResults(50L)
                    .setOrder("date")
                    .setPageToken(nextPageToken)
                    // 时区：America/Los_Angeles
                    .setPublishedAfter(new com.google.api.client.util.DateTime(startDate, TimeZone.getTimeZone("PST")))
                    .setPublishedBefore(new com.google.api.client.util.DateTime(endDate, TimeZone.getTimeZone("PST")))
                    .execute();
            log.info(response);
            log.info("pageInfo: " + response.getPageInfo());
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回示例
     *
     * {
     * 	"kind": "youtube#searchListResponse",
     * 	"etag": "tfxoGyrw_1xR_5h4y4GixF2c_jI",
     * 	"regionCode": "GB",
     * 	"pageInfo": {
     * 		"totalResults": 19,
     * 		"resultsPerPage": 20
     *        },
     * 	"items": [{
     * 			"kind": "youtube#searchResult",
     * 			"etag": "9xLDZWfuhN99zJgoeVG0kJlq2Lk",
     * 			"id": {
     * 				"kind": "youtube#playlist",
     * 				"playlistId": "PL88DA2B6B3C540875"
     *            },
     * 			"snippet": {
     * 				"publishedAt": "2011-12-16T00:01:01Z",
     * 				"channelId": "UCZYTClx2T1of7BRZ86-8fow",
     * 				"title": "SciShow Infusion",
     * 				"description": "Every other Wednesday, get in depth science to feed your brain.",
     * 				"thumbnails": {
     * 					"default": {
     * 						"url": "https://i.ytimg.com/vi/9S7H8TlkBC4/default.jpg",
     * 						"width": 120,
     * 						"height": 90
     *                    },
     * 					"medium": {
     * 						"url": "https://i.ytimg.com/vi/9S7H8TlkBC4/mqdefault.jpg",
     * 						"width": 320,
     * 						"height": 180
     *                    },
     * 					"high": {
     * 						"url": "https://i.ytimg.com/vi/9S7H8TlkBC4/hqdefault.jpg",
     * 						"width": 480,
     * 						"height": 360
     *                    }
     *                },
     * 				"channelTitle": "SciShow",
     * 				"liveBroadcastContent": "none",
     * 				"publishTime": "2011-12-16T00:01:01Z"
     *            }
     *        },
     *        {
     * 			"kind": "youtube#searchResult",
     * 			"etag": "U4o0AQE0KFoJB8IflLWXYCls7UM",
     * 			"id": {
     * 				"kind": "youtube#video",
     * 				"videoId": "ZBpOYnP72RU"
     *            },
     * 			"snippet": {
     * 				"publishedAt": "2011-12-02T23:28:26Z",
     * 				"channelId": "UCZYTClx2T1of7BRZ86-8fow",
     * 				"title": "SciShow Preview",
     * 				"description": "Hank gives you a sneak peek into SciShow - a new YouTube program that just might change the way you look at your planet, your universe, and even yourself.",
     * 				"thumbnails": {
     * 					"default": {
     * 						"url": "https://i.ytimg.com/vi/ZBpOYnP72RU/default.jpg",
     * 						"width": 120,
     * 						"height": 90
     *                    },
     * 					"medium": {
     * 						"url": "https://i.ytimg.com/vi/ZBpOYnP72RU/mqdefault.jpg",
     * 						"width": 320,
     * 						"height": 180
     *                    },
     * 					"high": {
     * 						"url": "https://i.ytimg.com/vi/ZBpOYnP72RU/hqdefault.jpg",
     * 						"width": 480,
     * 						"height": 360
     *                    }
     *                },
     * 				"channelTitle": "SciShow",
     * 				"liveBroadcastContent": "none",
     * 				"publishTime": "2011-12-02T23:28:26Z"
     *            }
     *        },
     *        {
     * 			"kind": "youtube#searchResult",
     * 			"etag": "Ch5_JA70eQjClEd2eNiMlr8SeGg",
     * 			"id": {
     * 				"kind": "youtube#channel",
     * 				"channelId": "UCZYTClx2T1of7BRZ86-8fow"
     *            },
     * 			"snippet": {
     * 				"publishedAt": "2011-10-20T16:11:13Z",
     * 				"channelId": "UCZYTClx2T1of7BRZ86-8fow",
     * 				"title": "SciShow",
     * 				"description": "SciShow explores the unexpected. Seven days a week, Hank Green, Michael Aranda, and Olivia Gordon delve into the scientific subjects that defy our ...",
     * 				"thumbnails": {
     * 					"default": {
     * 						"url": "https://yt3.ggpht.com/a/AATXAJz7l1SVybs0VPRCnd0xSIoSIMnTTd9ABp8bSuRM=s88-c-k-c0xffffffff-no-rj-mo"
     *                    },
     * 					"medium": {
     * 						"url": "https://yt3.ggpht.com/a/AATXAJz7l1SVybs0VPRCnd0xSIoSIMnTTd9ABp8bSuRM=s240-c-k-c0xffffffff-no-rj-mo"
     *                    },
     * 					"high": {
     * 						"url": "https://yt3.ggpht.com/a/AATXAJz7l1SVybs0VPRCnd0xSIoSIMnTTd9ABp8bSuRM=s800-c-k-c0xffffffff-no-rj-mo"
     *                    }
     *                },
     * 				"channelTitle": "SciShow",
     * 				"liveBroadcastContent": "upcoming",
     * 				"publishTime": "2011-10-20T16:11:13Z"
     *            }
     *        }
     * 	]
     * }
     */
}
