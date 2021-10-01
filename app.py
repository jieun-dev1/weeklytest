from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient

app = Flask(__name__)

client = MongoClient("mongodb://localhost:27017/")
db = client.dbStock
col.find_one({'_id':ObjectId('5f6d775c29be48f7e50ea68e')})


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/post', methods=['POST'])
def save_post():
        title_receive = request.form['title_give']
        content_receive = request.form['content_give']

        doc = {
            'title': title_receive,
            'content': content_receive,
            # 'reg_date':

        }

        db.codingtest.insert_one(doc)

        return jsonify({'result': 'success', 'msg': '포스팅 성공!'})

@app.route('/post', methods=['GET'])
def get_post():
    items = list(db.codingtest.find({}, {'_id': False}))
    return jsonify({"result": "success", 'items': items})


@app.route('/post', methods=['DELETE'])
def delete_post():
    return {"result": "success"}


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)