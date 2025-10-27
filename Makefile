d-build:
	docker build -t ktor-sample:latest .

d-run:
	docker run --rm -p 8080:8080 ktor-sample:latest