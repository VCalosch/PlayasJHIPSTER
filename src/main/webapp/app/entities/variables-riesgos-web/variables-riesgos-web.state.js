(function() {
    'use strict';

    angular
        .module('playasApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('variables-riesgos-web', {
            parent: 'entity',
            url: '/variables-riesgos-web',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'playasApp.variables_riesgos_web.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/variables-riesgos-web/variables-riesgos-webs.html',
                    controller: 'Variables_riesgos_webController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('variables_riesgos_web');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('variables-riesgos-web-detail', {
            parent: 'variables-riesgos-web',
            url: '/variables-riesgos-web/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'playasApp.variables_riesgos_web.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/variables-riesgos-web/variables-riesgos-web-detail.html',
                    controller: 'Variables_riesgos_webDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('variables_riesgos_web');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Variables_riesgos_web', function($stateParams, Variables_riesgos_web) {
                    return Variables_riesgos_web.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'variables-riesgos-web',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('variables-riesgos-web-detail.edit', {
            parent: 'variables-riesgos-web-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/variables-riesgos-web/variables-riesgos-web-dialog.html',
                    controller: 'Variables_riesgos_webDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Variables_riesgos_web', function(Variables_riesgos_web) {
                            return Variables_riesgos_web.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('variables-riesgos-web.new', {
            parent: 'variables-riesgos-web',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/variables-riesgos-web/variables-riesgos-web-dialog.html',
                    controller: 'Variables_riesgos_webDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id_Registro_Riesgo: null,
                                tipo_Variable: null,
                                direccion: null,
                                oleaje_Tipo: null,
                                oleaje_tamano: null,
                                viento_velocidad: null,
                                marea_Rango: null,
                                marea_Momento: null,
                                fecha: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('variables-riesgos-web', null, { reload: 'variables-riesgos-web' });
                }, function() {
                    $state.go('variables-riesgos-web');
                });
            }]
        })
        .state('variables-riesgos-web.edit', {
            parent: 'variables-riesgos-web',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/variables-riesgos-web/variables-riesgos-web-dialog.html',
                    controller: 'Variables_riesgos_webDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Variables_riesgos_web', function(Variables_riesgos_web) {
                            return Variables_riesgos_web.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('variables-riesgos-web', null, { reload: 'variables-riesgos-web' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('variables-riesgos-web.delete', {
            parent: 'variables-riesgos-web',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/variables-riesgos-web/variables-riesgos-web-delete-dialog.html',
                    controller: 'Variables_riesgos_webDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Variables_riesgos_web', function(Variables_riesgos_web) {
                            return Variables_riesgos_web.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('variables-riesgos-web', null, { reload: 'variables-riesgos-web' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
