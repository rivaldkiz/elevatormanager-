error id: file:///C:/Users/franc/Desktop/elevatormanager/app/views/components/forms/inputField.scala.html:scala/Predef.`???`().
file:///C:/Users/franc/Desktop/elevatormanager/app/views/components/forms/inputField.scala.html
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1218
uri: file:///C:/Users/franc/Desktop/elevatormanager/app/views/components/forms/inputField.scala.html
text:
```scala
@(
  name: String,
  label: String,
  inputType: String = "text",
  value: String = "",
  error: Option[String] = None,
  icon: Option[String] = None,
  placeholder: String = "",
  id: Option[String] = None,
  required: Boolean = false
)

@defining(id.getOrElse(name)) { fieldId =>
  @defining(error.isDefined) { hasError =>
    <div class="flex flex-col">
      <label class="font-label-md text-label-md text-on-surface mb-1.5" for="@fieldId">
        @label
      </label>

      <div class="relative flex items-center">
        @icon.map { iconName =>
          <span class="material-symbols-outlined absolute left-3.5 @(if(hasError) "text-error" else "text-on-surface-variant") text-xl pointer-events-none">
            @iconName
          </span>
        }

        <input
          class="w-full h-12 @(if(icon.isDefined) "pl-11" else "pl-4") @(if(hasError) "pr-10 bg-error-container/30" else "pr-4 bg-surface-container-highest") rounded-lg text-on-surface font-body-lg text-body-lg focus:outline-none shadow-sm transition duration-150"
          id="@fieldId"
          name="@name"
          type="@inputType"
          value="@value"
          @if(placeholder.nonEmpty){ placeh@@older="@placeholder" }
          @if(required){ required }
        />

        @if(hasError) {
          <span class="material-symbols-outlined absolute right-3.5 text-error text-xl">
            error
          </span>
        }
      </div>

      @error.map { errorMsg =>
        <div class="flex items-center gap-1.5 mt-1.5 text-error font-label-sm text-label-sm">
          <span class="material-symbols-outlined text-sm">warning</span>
          <span>@errorMsg</span>
        </div>
      }
    </div>
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 