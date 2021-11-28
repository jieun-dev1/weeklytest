from datetime import datetime

from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient

app = Flask(__name__)

client = MongoClient("mongodb://localhost:27017/")
db = client.dbStock


@app.route('/')
def index():
    return render_template('index.html')


idx = db.post.count()


@app.route('/post', methods=['POST'])
def save_post():
    title = request.form.get('title')
    content = request.form.get('content')
    idx = idx + 1
    post = {
            'idx': idx,
            'title': title,
            'content': content,
            'reg_date': datetime.now()
        }

    db.post.insert_one(post)
    return {"result": "success"}


@app.route('/post', methods=['GET'])
def get_post():
    posts = list(db.post.find({}, {'_id': False}).sort([("reg_date", -1)]))
    for a in posts:
        a['reg_date'] = a['reg_date'].strftime('%Y.%m.%d %H:%M:%S')

    return jsonify({"posts": posts})


@app.route('/post', methods=['DELETE'])
def delete_post():
    idx = request.args.get('idx')
    db.post.delete_one({'idx': int(idx)})
    return {"result": "success"}

@app.route('/update_read', methods=['POST'])
def update_like():
        id_receive = request.form["id_give"]
        action_receive = request.form["action_give"]
        doc = {
            "_id": id_receive,
            "action": action_receive
        }
        if action_receive == "read":
            db.reads.insert_one(doc)
        else:
            count = db.reads.count_documents({"_id": id_receive, "action": action_receive})
        return jsonify({"result": "success", 'msg': 'updated', "count": count})


# @app.route('/post', methods=['DELETE'])
# def edit_post():
#     idx = request.args.get('idx')
#     db.post.edit_one({'idx': int(idx)})
#     return {"result": "success"}


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8080)
