# Видео камеры
[![Build Status](https://travis-ci.org/ReyBos/job4j_camera.svg?branch=master)](https://travis-ci.org/ReyBos/job4j_camera) &nbsp;&nbsp;
[![codecov](https://codecov.io/gh/ReyBos/job4j_camera/branch/master/graph/badge.svg?token=ADQNARS17I)](https://codecov.io/gh/ReyBos/job4j_camera)
<p>
    Необходимо написать код для получения и агрегации данных из нескольких сервисов.
    <br><br>Получение списка доступных видеокамер:
    <br>http://www.mocky.io/v2/5c51b9dd3400003252129fb5
    <br>Ответ состоит из массива объектов, содержащих поля:
</p>
<ul>
    <li>id - число, идентификатор камеры</li>
    <li>sourceDataUrl - строка, ссылка для получения данных источника.</li>
    <li>tokenDataUrl - строка, ссылка для получения токенов безопасности по камере.</li>
</ul>
<p>
    Формат данных в ответе на запрос на URL из поля sourceDataUrl:
</p>
<ul>
    <li>urlType - строка, тип ссылки на видеопоток. Возможные значения: "LIVE", "ARCHIVE"</li>
    <li>videoUrl - строка, ссылка на видеопоток</li>
</ul>
<p>
    Формат данных в ответе на запрос на URL из поля tokenDataUrl:
</p>
<ul>
    <li>value - строка, токен безопасности</li>
    <li>ttl - число, время жизни токена</li>
</ul>
<p>
    Необходимо сагрегировать данные по каждой камере. Ожидаемый результат:
</p>
<pre>
<code>[
    {
        "id": 1,
        "urlType": "LIVE",
        "videoUrl": "rtsp://127.0.0.1/1",
        "value": "fa4b588e-249b-11e9-ab14-d663bd873d93",
        "ttl": 120
    },
    {
        "id": 3,
        "urlType": "ARCHIVE",
        "videoUrl": "rtsp://127.0.0.1/3",
        "value": "fa4b5d52-249b-11e9-ab14-d663bd873d93",
        "ttl": 120
    },
    {
        "id": 20,
        "urlType": "LIVE",
        "videoUrl": "rtsp://127.0.0.1/20",
        "value": "fa4b5f64-249b-11e9-ab14-d663bd873d93",
        "ttl": 180
    },
    {
        "id": 2,
        "urlType": "ARCHIVE",
        "videoUrl": "rtsp://127.0.0.1/2",
        "value": "fa4b5b22-249b-11e9-ab14-d663bd873d93",
        "ttl": 60
    }
]</code>
</pre>
<p>
    При написании кода надо учитывать потенциально большие объемы данных, то есть сбор
    и агрегация должны выполняться в несколько потоков и как можно меньше блокироваться
    (на операциях I/O или ожидании данных другого запроса)
</p>
<p>
    Решение должно быть опубликовано на гитхабе и подкреплено юнит-тестами.
</p>