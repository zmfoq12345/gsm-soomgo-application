package com.example.gsmgosu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_choose_field.*

data class field(val imageResource : Int, val name: String)

class ChooseField : AppCompatActivity() {

    var field_app = arrayListOf<FieldModel>(
        FieldModel("Android", R.drawable.chooseandroid),
        FieldModel("IOS", R.drawable.chooseios),
        FieldModel("App Design", R.drawable.chooseappdesign),
        FieldModel("Rx Java", R.drawable.chooserxjava),
        FieldModel("Flutter", R.drawable.chooseflutter),
        FieldModel("Hilt", R.drawable.choosehilt),
    )

    var field_design = arrayListOf<FieldModel>(
        FieldModel("3D 모델링", R.drawable.choose3dmodeling),
        FieldModel("PhotoShop", R.drawable.choosephotoshop),
        FieldModel("illustrator", R.drawable.chooseillustrator),
        FieldModel("Sketch", R.drawable.chooseskectch),
        FieldModel("XD", R.drawable.chooseadobexd),
        FieldModel("Figma", R.drawable.choosefigma),
    )

    var field_web = arrayListOf<FieldModel>(
        FieldModel("Node.js", R.drawable.choosenodejs),
        FieldModel("Django", R.drawable.choosedjango),
        FieldModel("Spring", R.drawable.choosespring),
        FieldModel("Flask", R.drawable.chooseflask),
        FieldModel("Ruby", R.drawable.chooseruby),
        FieldModel("ETC", R.drawable.chooseetc),
    )

    var field_server = arrayListOf<FieldModel>(
        FieldModel("JavaScript", R.drawable.choosejavascript),
        FieldModel("React", R.drawable.choosereact),
        FieldModel("Vue.js", R.drawable.choosevuejs),
        FieldModel("TypeScript", R.drawable.choosetypescript),
        FieldModel("Angular.Js", R.drawable.chooseangularjs),
        FieldModel("ETC", R.drawable.chooseetc),
    )




    var modelList = ArrayList<FieldModel>()



    private lateinit var recyclerAdapter: FieldAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_field)

        init()

    }

    private fun init() {
        //this.modelList.add(FieldModel(name = "IOS", profileImage = R.drawable.chooseios))

        recyclerAdapter = FieldAdapter()

        var fieldName = intent.getStringExtra("field")
        //recyclerAdapter.submitList(this.modelList)
        when(fieldName){
            "모바일 앱" -> recyclerAdapter.submitList(this.field_app)
            "디자이너" -> recyclerAdapter.submitList(this.field_design)
            "웹" -> recyclerAdapter.submitList(this.field_web)
            "서버" -> recyclerAdapter.submitList(this.field_server)

        }

        // 리사이클러뷰 설정
        dataList.apply {

            // 리사이클러뷰 방향 등 설정
            layoutManager =
                GridLayoutManager(this@ChooseField, 2, GridLayoutManager.VERTICAL, false)

            // 어답터 장착
            adapter = recyclerAdapter
        }
    }
}