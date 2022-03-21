-------------------------
--     惰性删除奖池      --
-------------------------

-- KEYS[1]=过期时间前缀名称
local recordTimeStamp = redis.call("get", KEYS[1]);
-- 过期时间的步长
local refreshStep = KEYS[2];
-- 当前时间戳
local currentTimeStamp = KEYS[3];
-- 当前僵持金额
local jackpotPool = 0;

-- 第一次初始化准备
if (type(recordTimeStamp) ~= 'boolean') then
    -- recordTimeStamp + refreshStep >= currentTimeStamp => 表示未过期
    if ((tonumber(recordTimeStamp) + tonumber(refreshStep)) >= tonumber(currentTimeStamp)) then
        -- 要获取奖池的名称
        jackpotPool = redis.call("get", KEYS[4]);
    end

end

-- 无论奖池是否有值，全部清空
redis.call("set", KEYS[1], currentTimeStamp);
redis.call("set", KEYS[4], 0);
return jackpotPool;

