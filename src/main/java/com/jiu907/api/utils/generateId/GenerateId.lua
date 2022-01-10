-------------------------
-- Lua脚本__生成UserId --
-------------------------


-- 因为redis不允许执行一些不确定的命令，所以引入此操作
redis.replicate_commands();


-- 前缀池大小
local prefixPoolSize = 10000;
-- ID池大小
local idPoolSize     = 10000;
-- 当前前缀Key
local currentPrefixName='currentPrefix';
-- 当前前缀池
local prefixPoolName='prefixPool';
-- 当前Id池
local idPoolName='idPool';
-- 当前前缀
local currentPrefixValue=redis.call("get",KEYS[1]);

-- 1.初始化前缀和ID号池，利用Redis的murmurhash2算法无序生成
--   初始化currentPrefix
if(type(currentPrefixValue)=='boolean') then
    redis.call('del',prefixPoolName,idPoolName);
    for i=1,prefixPoolSize do
         redis.call('sadd',prefixPoolName,i);
         redis.call('sadd',idPoolName,i);
    end
    currentPrefixValue = redis.call('spop',prefixPoolName);
    redis.call('set',currentPrefixName,currentPrefixValue);
end

-- 2.取号操作




return currentPrefixValue;