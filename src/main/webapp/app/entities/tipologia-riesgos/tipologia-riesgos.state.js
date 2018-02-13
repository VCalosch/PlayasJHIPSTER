(function() {
    'use strict';

    angular
        .module('playasApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tipologia-riesgos', {
            parent: 'entity',
            url: '/tipologia-riesgos',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'playasApp.tipologia_riesgos.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipologia-riesgos/tipologia-riesgos.html',
                    controller: 'Tipologia_riesgosController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipologia_riesgos');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tipologia-riesgos-detail', {
            parent: 'tipologia-riesgos',
            url: '/tipologia-riesgos/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'playasApp.tipologia_riesgos.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipologia-riesgos/tipologia-riesgos-detail.html',
                    controller: 'Tipologia_riesgosDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipologia_riesgos');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Tipologia_riesgos', function($stateParams, Tipologia_riesgos) {
                    return Tipologia_riesgos.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tipologia-riesgos',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tipologia-riesgos-detail.edit', {
            parent: 'tipologia-riesgos-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipologia-riesgos/tipologia-riesgos-dialog.html',
                    controller: 'Tipologia_riesgosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipologia_riesgos', function(Tipologia_riesgos) {
                            return Tipologia_riesgos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipologia-riesgos.new', {
            parent: 'tipologia-riesgos',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipologia-riesgos/tipologia-riesgos-dialog.html',
                    controller: 'Tipologia_riesgosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                detalle_topologia: null,
                                id_Riesgo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tipologia-riesgos', null, { reload: 'tipologia-riesgos' });
                }, function() {
                    $state.go('tipologia-riesgos');
                });
            }]
        })
        .state('tipologia-riesgos.edit', {
            parent: 'tipologia-riesgos',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipologia-riesgos/tipologia-riesgos-dialog.html',
                    controller: 'Tipologia_riesgosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipologia_riesgos', function(Tipologia_riesgos) {
                            return Tipologia_riesgos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipologia-riesgos', null, { reload: 'tipologia-riesgos' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipologia-riesgos.delete', {
            parent: 'tipologia-riesgos',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipologia-riesgos/tipologia-riesgos-delete-dialog.html',
                    controller: 'Tipologia_riesgosDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Tipologia_riesgos', function(Tipologia_riesgos) {
                            return Tipologia_riesgos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipologia-riesgos', null, { reload: 'tipologia-riesgos' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
