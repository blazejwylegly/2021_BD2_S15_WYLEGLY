# 2021_BD2_S15_WYLEGLY
### Description
Project was created on behalf of University project - it's main goal was to familiarize team members with techniques of creating enterprise applications.
Due to strict university deadlines, some of the features have not been implemented in a acceptable way, and therefore require redesign and reimplementation - the project was mainly a learning experience.

### Git
1) DO NOT push any changes directly to main or develop branch

2) When implementing new features, create your own branch in the following pattern:
`feature/<concise_feature_description>`
(Always make sure to fetch the latest changes from the remote!)

3) When feature is ready, create a pull request onto `develop`
4) Any pull requests to `main` branch should be executed from `develop` *
5) `main` should be seen as 'production-ready branch'
   

   _(*) Even though we are not using any CI/CD pipelines (as of this day), we are trying to apply purist code integration approach_

You can visit http://<host_name>/swagger-ui/ (http://localhost:8080/swagger-ui/ locally)
