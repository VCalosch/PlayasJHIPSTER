(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('RiesgosDetailController', RiesgosDetailController);

    RiesgosDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Riesgos'];

    function RiesgosDetailController($scope, $rootScope, $stateParams, previousState, entity, Riesgos) {
        var vm = this;

        vm.riesgos = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('playasApp:riesgosUpdate', function(event, result) {
            vm.riesgos = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
