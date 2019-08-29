FROM golang:latest

RUN go get github.com/labstack/echo/...
RUN go get github.com/go-sql-driver/mysql
RUN go get github.com/go-gorp/gorp

WORKDIR /app
ADD . /app

CMD ["go", "run", "main.go", "router.go"]

