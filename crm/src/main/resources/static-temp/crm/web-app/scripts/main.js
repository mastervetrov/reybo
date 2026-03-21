$(document).ready(function() {

            $("#loadMain").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/main',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });

            $("#loadOrders").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/orders',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });

            $("#loadOrdersNew").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/orders/new',
                    method: 'GET',
                    success: function(response) {
                        $('#serviceOrderFormHtml').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });

            // orders new
                    function loadOrderForm() {
                        fetch('/orders/form/fragment')
                            .then(response => response.text())
                            .then(html => {
                                const overlay = document.getElementById('modalOverlay');
                                overlay.innerHTML = html;
                                overlay.classList.remove('hidden');
                            });
                    }

                    // Делегирование событий
                    document.addEventListener('click', function(e) {
                        const overlay = document.getElementById('modalOverlay');
                        if (overlay && e.target === overlay && !overlay.classList.contains('hidden')) {
                            overlay.classList.add('hidden');
                        }
                    });

                    document.addEventListener('keydown', function(e) {
                        if (e.key === 'Escape') {
                            const overlay = document.getElementById('modalOverlay');
                            if (overlay) overlay.classList.add('hidden');
                        }
                    });
            // orders new
            $("#loadSales").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/sales',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });

            $("#loadSettings").click(function() {
                event.preventDefault();
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/settings',
                    method: 'GET',
                    success: function(response) {
                        $('.right-part-admin-app').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });


            //menu-store-sub-item
            $("#loadStoreRemainder").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/store/remainder',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });

            $("#loadStoreReceipt").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/store/receipt',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadStoreMoves").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/store/moves',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadStoreReturnsPurchase").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/store/returns/purchase',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadStoreInventory").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/store/inventory',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadStoreReturnsSales").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/store/returns/sales',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadStoreCancellations").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/store/cancellations',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadStoreSettings").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/store/settings',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });




            //menu-finance-sub-item
            $("#loadFinanceCashes").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/finance/cashes',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadFinanceSalaries").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/finance/salaries',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadFinanceTransactions").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/finance/transactions',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadFinanceInvoices").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/finance/invoices',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });


            //menu-analytics-sub-item
            $("#loadAnalyticsReportsOrders").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/analytics/reports/orders',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });


            $("#loadAnalyticsCalls").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/analytics/calls',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadAnalyticsAdvertising").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/analytics/advertising',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });


            //menu-compendiums-sub-item
            $("#loadCompendiumsNomenclatures").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/compendiums/nomenclatures',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadCompendiumsWorks").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/compendiums/works',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadCompendiumsContractors").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/compendiums/contractors',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadCompendiumsDevices").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/compendiums/devices',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadCompendiumsCompleteSets").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/compendiums/complete-sets',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadCompendiumsProblems").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/compendiums/problems',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadCompendiumsCashIntemsIncome").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/compendiums/cash-items/income',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
            $("#loadCompendiumsUnits").click(function() {
                var url = $(this).attr('href');
                $.ajax({
                    url: '/crm/web-app/ajax/compendiums/units',
                    method: 'GET',
                    success: function(response) {
                        $('#panelContent').html(response);
                        history.pushState(null, '', url);
                    },
                    error: function() {
                        alert('Ошибка при загрузке содержимого!');
                    }
                });
            });
});

//Выбираем все ссылки кнопок меню содержащие класс
const menuLinks = document.querySelectorAll('.button-menu-item');
const menuSubLinks = document.querySelectorAll(".button-menu-sub-item");

menuLinks.forEach(link => {
    link.addEventListener('click', function(event) {
        event.preventDefault();
    });
});

menuSubLinks.forEach(link => {
    link.addEventListener('click', function(event) {
        event.preventDefault();
    });
});

document.getElementById('switchDisplayStoreSubItemContainer').addEventListener('click', function(event) {
    showStoreSubItem(); // Вызываем функцию для смены отображения
});

function showStoreSubItem() {
    const container = document.getElementById('storeSubItemContainer');

    // Проверяем текущее состояние отображения
    if (container.style.display === "none" || container.style.display === "") {
        container.style.display = "block"; // Показываем детали
    } else {
        container.style.display = "none"; // Скрываем детали
    }
}

document.getElementById('switchDisplayFinanceSubItemContainer').addEventListener('click', function(event) {
    showFinanceSubItem(); // Вызываем функцию для смены отображения
});

function showFinanceSubItem() {
    const container = document.getElementById('financeSubItemContainer');

    // Проверяем текущее состояние отображения
    if (container.style.display === "none" || container.style.display === "") {
        container.style.display = "block"; // Показываем детали
    } else {
        container.style.display = "none"; // Скрываем детали
    }
}


document.getElementById('switchDisplayAnalyticsSubItemContainer').addEventListener('click', function(event) {
    showAnalyticsSubItem(); // Вызываем функцию для смены отображения
});

function showAnalyticsSubItem() {
    const container = document.getElementById('analyticsSubItemContainer');

    // Проверяем текущее состояние отображения
    if (container.style.display === "none" || container.style.display === "") {
        container.style.display = "block"; // Показываем детали
    } else {
        container.style.display = "none"; // Скрываем детали
    }
}

document.getElementById('switchDisplayCompendiumsSubItemContainer').addEventListener('click', function(event) {
    showCompendiumsSubItem(); // Вызываем функцию для смены отображения
});

function showCompendiumsSubItem() {
    const container = document.getElementById('compendiumsSubItemContainer');

    // Проверяем текущее состояние отображения
    if (container.style.display === "none" || container.style.display === "") {
        container.style.display = "block"; // Показываем детали
    } else {
        container.style.display = "none"; // Скрываем детали
    }
}


//------------ПЕРЕКЛЮЧЕНИЕ МЕНЮ НАСТРОЕК-------------
function loadSettings(endpoint) {
    return function(event) {
        event.preventDefault();
        var url = $(this).attr('href');
        $.ajax({
            url: endpoint,
            method: 'GET',
            success: function(response) {
                $('.container-settings-content').html(response);
                history.pushState(null, '', url);
            },
            error: function(xhr, status, error) {
                alert('Ошибка при загрузке содержимого: ' + error);
            }
        });
    };
}

$(document).ready(function() {
    $(document).on('click', '#loadSettingsGeneral', loadSettings('/crm/web-app/ajax/settings/company/general/company'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsLocations', loadSettings('/crm/web-app/ajax/settings/company/locations'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsEmployees', loadSettings('/crm/web-app/ajax/settings/company/employees/employees'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsSafety', loadSettings('/crm/web-app/ajax/settings/company/safety'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsSubscription', loadSettings('/crm/web-app/ajax/settings/company/subscription'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsPrices', loadSettings('/crm/web-app/ajax/settings/system/prices'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsStatuses', loadSettings('/crm/web-app/ajax/settings/system/statuses/statuses'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsFormEditor', loadSettings('/crm/web-app/ajax/settings/system/form-editor'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsTemplateEditor', loadSettings('/crm/web-app/ajax/settings/system/template-editor'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsSms', loadSettings('/crm/web-app/ajax/settings/communication/sms'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsGeneral', loadSettings('/crm/web-app/ajax/settings/company/general/company'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsNotificationsEmployees', loadSettings('/crm/web-app/ajax/settings/communication/notifications/employees'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsTelephony', loadSettings('/crm/web-app/ajax/settings/communication/telephony'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsWidget', loadSettings('/crm/web-app/ajax/settings/integration/widget'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsApi', loadSettings('/crm/web-app/ajax/settings/integration/api'));
});

$(document).ready(function() {
    $(document).on('click', '#loadSettingsWebhook', loadSettings('/crm/web-app/ajax/settings/integration/webhook'));
});

//$(document).ready(function() {
//    var isLoading = false;
//    var currentPage = 1;
//
//    // Делегируем событие прокрутки
//    $(document).on('scroll', '.body-panel-content-down-common', function() {
//        console.log('Прокрутка внутри блока');
//
//        var $container = $(this);
//        var scrollTop = $container.scrollTop();
//        var scrollHeight = $container.prop('scrollHeight');
//        var clientHeight = $container.innerHeight();
//
//        // Проверка URL
//        if (window.location.href.indexOf('remainder') === -1) {
//            return;
//        }
//
//        // Проверка, что пользователь близко к низу
//        if (scrollTop + clientHeight >= scrollHeight - 50) {
//            if (isLoading) return; // избегаем повторных запросов
//            isLoading = true;
//            currentPage++;
//
//            $.ajax({
//                url: '/crm/web-app/ajax/store/remainder',
//                method: 'GET',
//                data: { pageNumber: currentPage },
//                success: function(response) {
//                    $('.t-body-remainder').append(response);
//                    isLoading = false;
//                },
//                error: function() {
//                    alert('Ошибка загрузки данных');
//                    isLoading = false;
//                }
//            });
//        }
//    });
//});

$(document).ready(function() {
    var isLoading = false;
    var isPageRemainderDone = false;
    var currentPage = 0;

    // Функция для привязки обработчика прокрутки
    function bindScrollHandler($element) {

        $element.on('scroll', function() {
            if (isPageRemainderDone) return;
            console.log('Прокрутка внутри блока');

            var $container = $(this);
            var scrollTop = $container.scrollTop();
            var scrollHeight = $container.prop('scrollHeight');
            var clientHeight = $container.innerHeight();

            if (window.location.href.indexOf('remainder') === -1) {
                return;
            }

            if (scrollTop + clientHeight >= scrollHeight - 500) {
                if (isLoading) return;
                isLoading = true;
                currentPage++;
                console.log('текущая страница внутри блока ' + currentPage);


                $.ajax({
                    url: '/crm/web-app/ajax/store/remainder/nextPage',
                    method: 'GET',
                    data: { pageNumber: currentPage },
                    success: function(response) {
                        $('.t-body-remainder').append(response);
                        if (response === '') {
                            console.log('все страницы загружены');
                            isPageRemainderDone = true;
                            currentPage = 0;
                        }
                        isLoading = false;
                    },
                    error: function() {
                        alert('Ошибка загрузки данных');
                        isLoading = false;
                    }
                });
            }
        });
    }

    // Создаем наблюдатель за DOM
    var observer = new MutationObserver(function(mutations) {
        mutations.forEach(function(mutation) {
            $(mutation.addedNodes).each(function() {
                if ($(this).is('.body-panel-content-down-common')) {
                    bindScrollHandler($(this));
                    console.log('Найден элемент .body-panel-content-down-common');
                    isPageRemainderDone = false;
                } else {
                    // Возможно, внутри есть нужный элемент
                    var innerElements = $(this).find('.body-panel-content-down-common');
                    if (innerElements.length > 0) {
                        bindScrollHandler(innerElements);
                        console.log('Найден элемент .body-panel-content-down-common');
                        isPageRemainderDone = false;
                        currentPage = 0;
                    }
                }
            });
        });
    });

    // Начинаем наблюдение за всем телом документа
    observer.observe(document.body, { childList: true, subtree: true });

    // Если элемент уже есть на странице при загрузке
    $('.body-panel-content-down-common').each(function() {
        bindScrollHandler($(this));
    });
});