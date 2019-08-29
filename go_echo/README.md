GO echoお試しプロジェクト

2way SQLのメモ
https://qiita.com/mattn/items/04c0db6cd9d9803f01cf

参考
https://log.shinofara.xyz/2018/02/go%E3%81%A7ddddomain-driven-design-%E3%83%89%E3%83%A1%E3%82%A4%E3%83%B3%E9%A7%86%E5%8B%95%E8%A8%AD%E8%A8%88%E3%82%92%E3%81%99%E3%82%8B%E3%81%A8%E3%81%97%E3%81%9F%E3%82%89%E3%81%A9%E3%82%93%E3%81%AA%E3%82%B3%E3%83%BC%E3%83%89%E8%A8%AD%E8%A8%88%E3%81%8C%E3%81%84%E3%81%84%E3%81%8B%E8%80%83%E3%81%88%E3%81%A6%E3%81%BF%E3%81%9F/
https://github.com/shinofara/study-ddd-golang/


go run main.go router.go

本来であればpackageのimportは絶対パスで行うべきだが、今回は簡略化のために相対パス

interfaceについても実装したいが、一旦後回しにする(repositoryで実現したい)

マックス登録可能件数のチェックとかするか

API一覧
・GET /api/post/:id
curl localhost:8080/api/topic/1?page=1

・DELETE /api/post/:id
curl -X DELETE localhost:8080/api/post/100 -d "{\"DeleteReason\":\"なんとなく\"}"  -H 'Content-Type:application/json' 

kotlinの場合
curl -X DELETE localhost:8080/post/100 -d "{\"delete_reason\":\"なんとなく\"}"  -H 'Content-Type:application/json' 


・POST /api/post
curl -X POST localhost:8080/api/post -d "{\"topic_id\":1, \"name\":\"山田 花子\", \"body\":\"インサートテスト\"}"  -H 'Content-Type:application/json'

・GET /api/loan
curl -X GET localhost:8080/api/loan
オブジェクト指向になっていないのが気になる
もっと良い書き方があるはずだが、、、


 docker build -t go-echo .
 docker run -d -p 8080:8080 --cpuset-cpus 1 --memory=1g go-echo
