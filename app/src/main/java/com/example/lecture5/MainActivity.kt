package com.example.lecture5

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(supportFragmentManager) {
            // получить все фрагменты
            fragments
            // найти фрагмент по тэгу (динамическое)
            findFragmentByTag("SomeTag")
            // найти фрагмент по id (статическое)
            findFragmentById(R.id.some_id)

            // отменить последную транзакцию
            popBackStack()
            // удалить все транзакции добавленные до транзакции с этим тэгом
            popBackStack("SomName", 0)
            // удалить все транзакции добавленные вместе с транзакцией с этим тэгом
            popBackStack("SomName", POP_BACK_STACK_INCLUSIVE)
            // количество транзакции
            backStackEntryCount
            getBackStackEntryAt(index)
            addOnBackStackChangedListener { }

            saveBackStack("SomeName")
            restoreBackStack("SomeName")
            clearBackStack("SomeName")

            // начать транзакцию
            beginTransaction().run {
                //добавляет фрагмент
                add(container, NewFragment, FR_TAG)
                // удаляет фрагмент. Если фрагмент не добавлен в backStack, он будет уничтожен
                remove(OldFragment)
                // заменяет текущий фрагмент фрагментом(remove() + add())
                replace(container, NewFragment, FR_TAG)
                // скрывает текущий фрагмент, но не уничтожает его
                hide(HiddenFragment)
                // показывает ранее скрытый фрагмент
                show(ShowedFragment)
                // удаляет представление из UI. В отличие от remove(),
                // состояние фрагмента по-прежнему сохраняется FragmentManager`ом
                detach(DetachFragment)
                // прикрепляет к UI и отображает
                attach(AttachFragment)

                // Добавляет фрагмент в backStack
                addToBackStack("NameOfTransaction")

                // завершить
                commit()
                // завершить с риском потерять состояние,
                // когда транзакция будет выполнена после onSaveInstanceState
                commitAllowingStateLoss()
                commitNow()
                commitNowAllowingStateLoss()
            }

            // Полезные extension функции
            commit { }
            commit(allowStateLoss = true) { }
            commitNow(allowStateLoss = true) { }

        }
    }


    class NewFragment : Fragment(R.layout.new_fragment) {

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // work with view
        }


        companion object {

            private const val PARAM_1 = "param1"
            private const val PARAM_2 = "param2"

            fun newInstance(param1: Int, param2: String) =
                NewFragment().apply {
                    arguments = bundleOf(
                        PARAM_1 to param1,
                        PARAM_2 to param2
                    )
                }
        }
    }
}