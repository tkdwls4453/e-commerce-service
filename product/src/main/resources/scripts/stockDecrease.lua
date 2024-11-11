-- KEYS: 각 아이템의 재고 키 (예: {"item:<itemId1>:stock", "item:<itemId2>:stock", ...})
-- ARGV: 각 아이템의 감소시킬 재고 수량 (예: {"3", "5", ...})

-- 입력 데이터 유효성 검사
if #KEYS ~= #ARGV then
    return redis.error_reply("Mismatch between keys and arguments")
end

-- 각 아이템의 재고가 충분한지 확인
for i = 1, #KEYS do
    local stock = tonumber(redis.call("GET", KEYS[i]))
    local decrement = tonumber(ARGV[i])

     -- 재고가 없는 경우 또는 충분하지 않은 경우 오류 반환
     if not stock then
         return "ERROR: Stock not found for item at index " .. i
     elseif stock < decrement then
         return "ERROR: Insufficient stock for item at index " .. i
     end
end

-- 모든 아이템의 재고가 충분한 경우에만 감소 실행
for i = 1, #KEYS do
    local new_stock = tonumber(redis.call("GET", KEYS[i])) - tonumber(ARGV[i])
    redis.call("SET", KEYS[i], new_stock)
end

return "All items updated successfully"
