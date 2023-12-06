const express = require('express');
const app = express();
const port = 8080;

const createBaseResponse = (requestCode, data) => ({
    code: 200,
    message: "OK",
    requestCode: requestCode,
    data: {
        header: {
            serviceId: "serviceId",
            userId: "",
            errMessage: "",
            code: "IB00013",
            message: "정상 조회되었습니다.!",
            repeat: 0
        },
        body: data
    }
});

const createPerson = (name, age, address) => ({
    name: name,
    age: age,
    address: address
});

const personList = [
    createPerson("홍길동", 20, "서울특별시 강남구"),
    createPerson("김철수", 24, "인천광역시 미추홀구"),
    createPerson("마이콜", 28, "대구광역시 수성구"),
    createPerson("제임스", 32, "용인시 기흥구"),
];

const createCity = (id, name, code) => ({
    id: id,
    name: name,
    code: code,
});

const cityList = [
    createCity(1, "서울특별시 강남구", 0o1234),
    createCity(2, "인천광역시 미추홀구", 23512),
    createCity(3, "대구광역시 수성구", 61243),
    createCity(4, "용인시 기흥구", 82345),
];

app.use(express.json());

app.route("/persons")
    .get((req, res) => {
        res.send(createBaseResponse(req.query.requestCode, personList));
    })
    .post((req, res) => {
        const body = req.body;
        personList.push(createPerson(body.name, body.age, body.address));
        res.status(201).send(createBaseResponse(body.requestCode, {
            message: "새로운 Person 을 생성했습니다."
        }));
    });


app.route("/cities")
    .get((req, res) => {
        res.send(createBaseResponse(req.query.requestCode, cityList));
    })
    .post((req, res) => {
        const body = req.body;
        cityList.push(createCity(cityList.length + 1, body.name, body.code));
        res.status(201).send(createBaseResponse(body.requestCode, {
            message: "새로운 City 를 생성했습니다."
        }));
    });

app.route("/cities/:id")
    .delete((req, res) => {
        const cityId = req.params.id;
        const body = req.body;
        const index = cityList.findIndex((value) => value.id === Number(cityId));

        if (index < 0 || index >= cityList.length) {
            res.status(400).send(createBaseResponse(body.requestCode, {
                message: "존재하지 않는 id 입니다."
            }));

            return;
        }

        cityList.splice(index, 1);
        res.status(201).send(createBaseResponse(body.requestCode, {
            message: `${cityId} 번 City 를 삭제했습니다.`
        }));
    });

app.listen(port)