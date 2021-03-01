FROM library/postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD root
ENV POSTGRES_DB pirate
COPY src/main/resources/db/migration/V1__Pirate.sql /docker-entrypoint-initdb.d/
EXPOSE 5433

# docker build -t pirates .
# docker run --rm --name test -p 5433:5432 pirates