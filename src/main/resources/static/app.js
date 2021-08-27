let app = angular.module('app',['ngMaterial']);

app.config(function($mdThemingProvider) {

    // Configure a dark theme with primary foreground yellow

    $mdThemingProvider.theme('docs-dark', 'default')
        .primaryPalette('yellow')
        .dark();

});

app.config(function($mdDateLocaleProvider) {
    $mdDateLocaleProvider.formatDate = function(date) {
        return moment(date).format('MM/DD/YYYY');
    };
});

app.controller('MovieCRUDCtrl', ['$scope','MovieCRUDService','$mdToast','$window',
        function ($scope,MovieCRUDService,$mdToast,$window) {
    let stompClient = null;
    $scope.genres = [];
    $scope.languajes = [];
    $scope.movie = {};

    $scope.clearMovie = function(){
        $scope.movie = {
            id:0,
            title:'',
            year: '',
            rated: '',
            released: new Date(),
            runtime: 0,
            genre: {id:0,code:'',name:''},
            director: {
                id:0,
                firstName: '',
                lastName:'',
                code:''
            },
            actors: [
                {
                    id:0,
                    firstName: '',
                    lastName:'',
                    code:''
                }
            ],
            plot: '',
            languajes: [],
            imdbRating: '',
            type: ''
        };
    };
    $scope.clearMovie();

    $scope.openTabXML = function (id) {
        $window.open('http://localhost:8080/movies/'+id, '_blank');
    };

    $scope.openTabJSON = function (id) {
        $window.open('http://localhost:8080/movies/'+id, '_blank');
    };

    $scope.addActor = function(){
        $scope.movie.actors.push({
            id:0,
            firstName:'',
            lastName:'',
            code:''
        })
    };

    $scope.setMovie = function(movie){
        $scope.movie = angular.extend({}, movie);
        console.log($scope.movie);
    };

    $scope.saveUpdateMovie = function(){
        $scope.movie.released = moment( $scope.movie.released).format('MM/DD/YYYY');
        if($scope.movie.id == 0){
            MovieCRUDService.createMovie($scope.movie)
        }else{
            MovieCRUDService.updateMovie($scope.movie)
        }
        $scope.clearMovie();
    }

    $scope.getAllLanguajes = function () {
        MovieCRUDService.getAllLanguajes()
            .then(function success(response) {
                $scope.languajes = response.data;
            })
    };

    $scope.getAllGenres = function () {
        MovieCRUDService.getAllGenres()
            .then(function success(response) {
                $scope.genres = response.data;
            })
    }

    $scope.getAllMovies = function () {
        MovieCRUDService.getAllMovies()
            .then(function success(response){
                    $scope.movies = response.data;
                    $scope.message='';
                    $scope.errorMessage = '';
                },
                function error (response ){
                    $scope.message='';
                    $scope.errorMessage = 'Error getting movies!';
                });
    }





    function connect() {
        let socket = new SockJS('/notifications');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/notifications/message', function (message) {
                $scope.showMessage(JSON.parse(message.body).content);
            });
        });
    }


    $scope.showMessage =function(message) {
        $mdToast.show(
            $mdToast.simple()
                .textContent(message)
                .position('top right')
                .hideDelay(3000)
        );
        $scope.getAllMovies();
    }

    connect();
    $scope.getAllMovies();
    $scope.getAllGenres();
    $scope.getAllLanguajes();

    $mdToast.show(
        $mdToast.simple()
            .textContent('Welcome!')
            .position('top right')
            .hideDelay(3000)
    );


}]);

app.service('MovieCRUDService', [ '$http', function($http) {

    this.getUser = function getUser(userId) {
        return $http({
            method : 'GET',
            url : 'users/' + userId
        });
    }
} ]);


app.service('MovieCRUDService',['$http', function ($http) {

    this.getUser = function getUser(userId){
        return $http({
            method: 'GET',
            url: 'users/'+userId
        });
    }

    this.addUser = function addUser(name, email){
        return $http({
            method: 'POST',
            url: 'users',
            data: {name:name, email:email}
        });
    }

    this.deleteUser = function deleteUser(id){
        return $http({
            method: 'DELETE',
            url: 'users/'+id
        })
    }

    this.createMovie = function createMovie(movie){
        return $http({
            method: 'POST',
            url: 'movies/',
            data: movie
        })
    }

    this.updateMovie = function updateMovie(movie){
        return $http({
            method: 'PUT',
            url: 'movies/',
            data: movie
        })
    }

    this.getAllMovies = function getAllMovies(){
        return $http({
            method: 'GET',
            url: 'movies/'
        });
    }

    this.getAllGenres = function getAllGenres(){
        return $http({
            method: 'GET',
            url: 'genres/'
        });
    }

    this.getAllLanguajes = function getAllLanguajes(){
        return $http({
            method: 'GET',
            url: 'languajes/'
        });
    }

}]);