package com.example.sui.feature.data.util

import android.content.Context
import com.example.sui.feature.data.datasource.SuiDatabase
import com.example.sui.feature.domain.model.RelationType
import com.example.sui.feature.domain.model.Sui
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class ExcelImporter(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("import_prefs", Context.MODE_PRIVATE)

    fun importData() {
        // 检查数据是否已经导入
        val isDataImported = sharedPreferences.getBoolean("is_data_imported", false)
        if (isDataImported) {
            return // 数据已经导入，直接返回
        }

        CoroutineScope(Dispatchers.IO).launch {
            val inputStream: InputStream = context.assets.open("data/sui.csv")

            val reader = BufferedReader(InputStreamReader(inputStream))

            val myDao = SuiDatabase.getDatabase(context).dao
            val entities = mutableListOf<Sui>()

            reader.useLines { lines ->
                lines.drop(1).forEach { line ->
                    val tokens = line.split(",")

                    val column0 = tokens[0].toInt() // 世代
                    val column1 = tokens[1].toString() // 姓氏
                    val column2 = tokens[2].toString() // 字辈
                    val column3 = tokens[3].toString() // 名字
                    val column4 = tokens[4].toString() // 本人代号
                    val column5 = tokens[5].toString() // 上级代号
                    val column6 = tokens[6].toString() // 母亲代号
                    val column7 = tokens[7].toString() // 上级关系
                    val column8 = tokens[8].toInt() // 排行
                    val column9 = tokens[9].toString() // 生辰(公历)
                    val column10 = tokens[10].toString() // 生辰(农历)
                    val column11 = tokens[11].toString() // 居住地
                    val column12 = tokens[12].toString() // 卒殁标识
                    val column13 = tokens[13].toString() // 殁辰(公历)
                    val column14 = tokens[14].toString() // 殁辰(农历)
                    val column15 = tokens[15].toString() // 葬地
                    val column16 = tokens[16].toString() // 字号别称
                    val column17 = tokens[17].toString() // 简介
                    val column18 = tokens[18].toString() // 职业
                    val column19 = tokens[19].toString() // 个人信息
                    val column20 = tokens[20].toString() // 小注

                    val relationType = when (column7) {
                        "子" -> RelationType.son
                        "女" -> RelationType.daughter
                        "配" -> RelationType.spouse
                        else -> RelationType.none
                    }

                    val birthday = when (column9) {
                        "0 " -> ""
                        else -> column9
                    }

                    val death = when (column13) {
                        "0 " -> ""
                        else -> column13
                    }

                    val entity = Sui(
                        generation = column0,
                        id = column4,
                        firstName = column3,
                        idSuperior = column5,
                        idMother = column6,
                        relationSuperiorType = relationType,
                        rank = column8,
                        residencePlace = column11,
                        birthday = birthday,
                        death = death,
                        noteOfDeath = column12,
                        burialPlace = column15,
                        alias = column16,
                        profession = column18,
                        personalInformation = column19,
                        introduction = column17,
                        note = column20
                    )

                    entities.add(entity)
                }
            }
            myDao.insertAll(entities)

            // 标记数据已导入
            sharedPreferences.edit().putBoolean("is_data_imported", true).apply()
        }
    }

}
