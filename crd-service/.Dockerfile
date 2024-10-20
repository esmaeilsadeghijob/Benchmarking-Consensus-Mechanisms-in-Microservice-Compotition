FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /target/*.jar /app/app.jar
RUN pwd && ls -l
EXPOSE 6666
CMD ["java", "-jar", "/app/app.jar"]