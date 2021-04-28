# 2021_BD2_S15_WYLEGLY
### Git
1) DO NOT push any changes directly to main or develop branch

2) When implementing new features, create your own branch in the following pattern:
`feature/<concise_feature_description>`
(Always make sure to fetch the latest changes from the remote!)

3) When feature is ready, create a pull request onto `develop`
4) Any pull requests to `main` branch should be executed from `develop` *
5) `main` should be seen as 'production-ready branch'
   

   _(*) Even though we are not using any CI/CD pipelines (as of this day), we are trying to apply purist code integration approach_