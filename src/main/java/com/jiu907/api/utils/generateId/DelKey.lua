--------------------------
-- Lua脚本__删除指定的key --
--------------------------

-- 要删除Key的数量
local delKeyNum=redis.call("get",ARGV[1]);

for i=1,delKeyNum do
    redis.call("del",KEY[i]);
end

return 1;