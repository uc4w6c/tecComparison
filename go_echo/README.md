GO echoお試しプロジェクト

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
