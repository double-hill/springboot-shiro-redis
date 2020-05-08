package com.xl.youliao.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author WeiC
 * @date 2020/5/7 10:29
 */
public class RedisSessionDao extends CachingSessionDAO {

    //public static final String SESSION_KEY = "session_key";
    @Value("${redissession.time}")
    private String expireTime;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void doUpdate(Session session) {
        this.saveSession(session);
    }

    @Override
    protected void doDelete(Session session) {
        if(session == null || session.getId() == null){
            return;
        }
        //缓存删除
        //redisTemplate.boundHashOps(SESSION_KEY).delete(session.getId());
        redisTemplate.delete(session.getId());
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session,sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if(sessionId == null){
            return null;
        }
        //return (Session)redisTemplate.boundHashOps(SESSION_KEY).get(sessionId);
        return (Session)redisTemplate.boundValueOps(sessionId).get();
    }

    /**
     * 保存会话到redis缓存中
     * @param session
     */
    private void saveSession(Session session) {
        if(session == null || session.getId() == null){
            return;
        }
        //redisTemplate.boundHashOps(SESSION_KEY).put(session.getId(),session);  hash 只能顶层key设置过期时间
        redisTemplate.boundValueOps(session.getId()).set(session,Integer.parseInt(expireTime), TimeUnit.MILLISECONDS);
    }


}
