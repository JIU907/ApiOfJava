-------------------------
--  优化版本--删除奖池    --
-------------------------

-- 当前奖池金额
local jackpotPool = redis.call("get", KEYS[1]);

-- 第一次初始化准备
if (type(jackpotPool) == 'boolean') then
    jackpotPool = 0;
end

redis.call("setex", KEYS[1], ARGV[1], 0);
-- 返回结果集
return jackpotPool;


